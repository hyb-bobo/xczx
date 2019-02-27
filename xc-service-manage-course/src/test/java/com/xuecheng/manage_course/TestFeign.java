package com.xuecheng.manage_course;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.manage_course.client.CmsPageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFeign {
    @Autowired
    CmsPageClient cmsPageClient; //接口代理对象，由Feign生成代理对象

    @Test
    public void testRibbon() {
        //发起远程调用
//        CmsPage cmsPage = cmsPageClient.findById("5a754adf6abb500ad05688d9");
//        System.out.println(cmsPage.toString() + "----------------------------");

        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("5a751fab6abb5044e0d1b1cf");//站点id
        cmsPage.setDataUrl("http://localhost:31200/course/courseview/");//数据模型url
        cmsPage.setPageName("http://localhost:31200/course/courseview/"+".html");//页面名称
        cmsPage.setPageAliase("就是课程名称就是课程名称就是课程名称");//页面别名，就是课程名称
        cmsPage.setPagePhysicalPath("5a751fab6abb5044e0d1b1cf");//页面物理路径
        cmsPage.setPageWebPath("5a751fab6abb5044e0d1b1cf");//页面webpath
        cmsPage.setTemplateId("5a751fab6abb5044e0d1b1cf");//页面模板id

        //远程调用cms
        CmsPageResult cmsPageResult = cmsPageClient.saveCmsPage(cmsPage);
        System.out.println(cmsPageResult);

    }


}
