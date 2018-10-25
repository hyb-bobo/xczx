package com.xuecheng.test.freemarker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by bobo on 2018/10/24.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFreemarker {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/banner")
    public String index_banner(Map<String, Object> map) {
        String dataUrl = "http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f";
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        Map body = forEntity.getBody();
        map.putAll(body);
        return "index_banner";
    }

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Test
    public void testGridFs() throws FileNotFoundException {
        //要存储的文件
        File file = new File("d:/index_banner.html");
        //定义输入流
        FileInputStream inputStram = new FileInputStream(file);
        //向GridFS存储文件
        org.bson.types.ObjectId objectId = gridFsTemplate.store(inputStram, "轮播图测试文件01", "");
        //得到文件ID
        String fileId = objectId.toString();
        System.out.println(file);
    }
}
