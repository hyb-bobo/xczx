package com.xuecheng.manage_cms.controller;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Java 执行Linux命令操作
 * 需要ssh2.jar
 */
public class SSH2Util {

	public static final String AA8153E60CC40153E61D6CC60009 = "4028aa8153e60cc40153e61d6cc60009";

	/**
	 * Java main方法直接执行SSH命令
	 * @param args
	 */
	public static void main(String[] args) {
		
		// 源主机相关信息
		String host = "192.168.40.129";// IP
		//host = "192.168.100.146";
//		host = "192.168.1.108";
		int port = 22;
		String username = "root";
		String passwd = "hyb481330";

		// 执行脚本命令
//		SSH2Util.testRunScript(host, port, username, passwd);
		// 批量拷贝文件
		SSH2Util.testCopyRemoteFilesToLocal(host, port, username, passwd);
	}
	
	/**
	 * 登录Linux系统执行命令
	 */
	public static void testRunScript(String host, int port, String username, String passwd) {
		
		String cmd = "ifconfig";// 查询IP
		cmd = "cat /etc/hosts";// 打开hosts文件
		//cmd = "cp /etc/hosts /home/myth/share";// copy /etc目录下hosts文件至/home/myth/share目录下
		/*
		// 下列命令不可用
		String cmd = "scp /etc/hosts root@192.168.100.146:/home";// copy本机文件至远程主机
		String cmd = "scp root@/hosts 192.168.100.146：/etc/hosts home";// copy远程主机文件至本机
		*/
		try {
			SSH2Util.runRomoteScript(host, port, username, passwd, cmd);
		} catch (Exception e) {
			System.err.println("执行远程脚本错误！！！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 拷贝远程文件至本地目录
	 */
	public static void testCopyRemoteFileToLocal(String host, int port, String username, String passwd) {
		
		String remoteFile = "/etc/hosts";// 源主机源文件
		String localDir = "E:\\app\\docs";// 目标主机目标路径Windows

//		localDir = "/opt/MyEclipse10/";// Linux(CentOS系统MyEclipse)中测试用

		// 目标主机目标路径CentOS
		SSH2Util.getFileFromRemote(host, port, username, passwd, remoteFile, localDir);
	}
	
	/**
	 * 拷贝远程文件至本地目录
	 */
	public static void testCopyRemoteFilesToLocal(String host, int port, String username, String passwd) {

		String[] fileNames = { "4028aa8153e5d36e0153e5d5920f0003.txt", "4028aa8153e60cc40153e61d6cc60009" };
//		String remoteFilePath = "/home/myth/share/file_upload/";
		String remoteFilePath = "/root/sql/";
		String localDir = "D:\\fromLiunx";// 目标主机目标路径Windows E:\\app\\docs
		//localDir = "/opt/MyEclipse10/";;// 目标主机目标路径CentOS

		SSH2Util.getFilesFromRemote(host, port, username, passwd, remoteFilePath, fileNames, localDir);
	}

	/**
	 * 执行远程的封存脚本
	 * 
	 * @param host 源主机hostname/IP
	 * @param port 源主机端口
	 * @param username 源主机登录用户名
	 * @param passwd 源主机用户密码
	 * @param cmd 命令
	 * @return
	 */
	public static List<String> runRomoteScript(String host, int port, String username, String passwd, String cmd) {
		List<String> result = new ArrayList<String>();
		try {
			Connection conn = new Connection(host, port);
			conn.connect();
			boolean isAuth = conn.authenticateWithPassword(username, passwd);
			if (isAuth == false) throw new RuntimeException("Exception: Insufficient Privilege(权限不够)!!!");
			Session sess = conn.openSession();
			sess.execCommand(cmd);
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			// 输出命令结果
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				result.add(line);
			}
			sess.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(SSH2Util.class.getName() + ".runRomoteScript() error!!!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 从远程服务器下载单个文件到本地文件夹
	 * 
	 * @param host 源主机hostname/IP
	 * @param port 源主机端口
	 * @param username 源主机登录用户名
	 * @param passwd 源主机用户密码
	 * @param remoteFile 源文件(带路径)
	 * @param localDir 目标路径
	 * @return
	 */
	public static boolean getFileFromRemote(String host, int port, String username, String passwd, String remoteFile, String localDir) {
		boolean msg = true;
		try {
			Connection conn = new Connection(host, port);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(username, passwd);
			if (isAuthenticated == false) {
				System.err.println(SSH2Util.class.getName() + ".getFileFromRemote() error(权限不够)!!!");
				return false;
			}
			File inputFile = new File(localDir);
			if (!inputFile.exists()) {// 如果文件夹不存在，则新建文件夹
				inputFile.mkdirs();
			}
			SCPClient scpClient = conn.createSCPClient();
			scpClient.get(remoteFile, localDir);
			conn.close();
		} catch (IOException e) {
			System.err.println(SSH2Util.class.getName() + ".getFileFromRemote() error(出现了IO错误)!!!");
			return false;
		}
		return msg;
	}

	/**
	 * 从远程主机批量copy文件至本机目标路径,适用于源文件不在同一文件夹
	 * 
	 * @param host 远程主机hostname/IP
	 * @param port 远程主机端口,默认22
	 * @param username 远程主机账户名,root等
	 * @param passwd 远程主机密码
	 * @param remoteFiles 远程主机源文件,包含地址路径
	 * @param localDir 本机目标路径
	 * @return
	 */
	public static boolean getFilesFromRemote(String host, int port, String username, String passwd, String[] remoteFiles, String localDir) {
		boolean msg = true;
		try {
			Connection conn = new Connection(host, port);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(username, passwd);
			if (isAuthenticated == false) {
				System.err.println(SSH2Util.class.getName() + ".getFileFromRemote() error(权限不够)!!!");
				return false;
			}
			File inputFile = new File(localDir);
			if (!inputFile.exists()) {// 如果文件夹不存在，则新建文件夹
				inputFile.mkdirs();
			}
			SCPClient scpClient = conn.createSCPClient();
			scpClient.get(remoteFiles, localDir);
			conn.close();
		} catch (IOException e) {
			System.err.println(SSH2Util.class.getName() + ".getFilesFromRemote() error(出现了IO错误)!!!");
			return false;
		}
		return msg;
	}
	
	/**
	 * 从远程主机批量copy文件至本机目标路径,适用于源文件在同一文件夹
	 * 
	 * @param host 远程主机hostname/IP
	 * @param port 远程主机端口,默认22
	 * @param username 远程主机账户名,root等
	 * @param passwd 远程主机密码
	 * @param remoteFilePath 远程主机源文件路径(最后字符为/)
	 * @param fileNames 远程主机源文件名
	 * @param localDir 本机目标路径
	 * @return
	 */
	public static boolean getFilesFromRemote(String host, int port, String username, String passwd, String remoteFilePath, String[] fileNames, String localDir) {
		boolean msg;
		String[] remoteFiles = new String[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			remoteFiles[i] = remoteFilePath + fileNames[i];
		}
		msg = getFilesFromRemote(host, port, username, passwd, remoteFiles, localDir);
		return msg;
	}

}