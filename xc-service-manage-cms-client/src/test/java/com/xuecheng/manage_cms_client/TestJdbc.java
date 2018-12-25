package com.xuecheng.manage_cms_client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by bobo on 2018/12/25.
 */
/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJdbc {

    @Test
    public void testJdbc() throws Exception {
//        String URL = "jdbc:mysql://192.168.40.129:3306/xc_course?characterEncoding=utf-8&useSSL=false";
//        String URL = "jdbc:mysql://192.168.40.129:3306/mysql?characterEncoding=utf-8&useSSL=false";
//        String USER = "root";
//        String PASSWORD = "123456";
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.40.129:3306/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");

        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
//        2.获得数据库链接
//        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from course_base");
        //4.处理数据库的返回结果(使用ResultSet类)
        while (rs.next()) {
            System.out.println(rs.getString("name") + " "
                    + rs.getString("users"));
        }

        //关闭资源
        rs.close();
        st.close();
        conn.close();
    }
}
