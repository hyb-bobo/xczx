package com.xuecheng.manage_course.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDao {
    @Autowired
    CourseBaseRepository courseBaseRepository;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeachplanMapper TeachplanMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void testCourseBaseRepository() {
        Optional<CourseBase> optional = courseBaseRepository.findById("402885816240d276016240f7e5000002");
        if (optional.isPresent()) {
            CourseBase courseBase = optional.get();
            System.out.println(courseBase);
        }

    }

    @Test
    public void testCourseMapper() {
        CourseBase courseBase = courseMapper.findCourseBaseById("402885816240d276016240f7e5000002");
        System.out.println(courseBase+"-------------------");

    }

    @Test
    public void testCourseMapper1() {
//        CourseBase courseBase = courseMapper.findCourseBaseById("402885816240d276016240f7e5000002");
        TeachplanNode teachplanNode = TeachplanMapper.selectList("4028e581617f945f01617f9dabc40000");
        System.out.println(teachplanNode);

    }

    @Test
    public void testfindList() {
//        CourseBase courseBase = courseMapper.findCourseBaseById("402885816240d276016240f7e5000002");
        CategoryNode categoryMapperList = categoryMapper.findList();
        System.out.println(categoryMapperList);

    }

    //测试分页
    @Test
    public void testPageHelper() {
        PageHelper.startPage(1, 10);//查询第一页，每页显示10条记录
        CourseListRequest courseListRequest = new CourseListRequest();
        courseListRequest.setCompanyId("10");
        courseListRequest.setName("java");
        courseListRequest.setTeachmode("2018-12-12 12:12:12");
        Page<CourseInfo> courseListPage = courseMapper.findCourseListPage(courseListRequest);
        List<CourseInfo> result = courseListPage.getResult();
        System.out.println(courseListPage);
        System.out.println("------");
        System.out.println(result);
    }

    @Test
    public void testPageHelpe0r() {
        PageHelper.startPage(1, 10);//查询第一页，每页显示10条记录
        Page<CourseBase> courseListPage = courseMapper.finfCourseBase();
        List<CourseBase> result = courseListPage.getResult();
        System.out.println(courseListPage);
        System.out.println("------");
        System.out.println(result);
    }
}
