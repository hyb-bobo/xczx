package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.AddCourseResult;
import com.xuecheng.framework.domain.course.response.CoursePublishResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bobo on 2018/12/27.
 */
@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {


    @Autowired
    CourseService courseService;

    //查询课程计划
    @Override
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachplanList(courseId);
    }

    /**
     * 课程营销查询
     * @param courseId
     * @return
     */
    @Override
    @GetMapping("/getCourseMarketById/{courseId}")
    public CourseMarket getCourseMarketById(@PathVariable("courseId") String courseId) {
        CourseMarket courseMarket = courseService.getCourseMarketById(courseId);
        return courseMarket;
    }

    //添加课程计划
    @Override
    @PostMapping("/teachplan/add")
    public ResponseResult addTeachplan(@RequestBody Teachplan teachplan) {
        return courseService.addTeachplan(teachplan);
    }

    /**
     * 更新课程基本信息
     * @param CourseBase
     * @return
     */
    @Override
    @PostMapping("/coursebase/updateCoursebase")
    public ResponseResult updateCoursebase(@RequestBody CourseBase CourseBase) {
        return courseService.updateCoursebase(CourseBase);
    }

    @Override
    @PostMapping("/coursebase/updateCourseMarket")
    public ResponseResult updateCourseMarket(@RequestBody CourseMarket courseMarket) {
        ResponseResult responseResult = courseService.updateCourseMarket(courseMarket);
        return responseResult;
    }

    @Override
    @GetMapping("/coursebase/list/{page}/{size}")
    @ResponseBody
    public QueryResponseResult findCourseList(@PathVariable("page")int page, @PathVariable("size")int size, CourseListRequest courseListRequest) {
        QueryResponseResult courseInfo = courseService.findCourseList(page,size,courseListRequest);
        return courseInfo;
    }

    /**
     * 根据id查询课程信息
     * @param courseid
     * @return
     */
    @Override
    @GetMapping("/getCourse/{courseid}")
    public CourseBase getCoursebaseById(@PathVariable("courseid") String courseid) {
        return courseService.getCoursebaseById(courseid);
    }

    //添加课程基础信息
    @Override
    @PostMapping("/coursebase/add")
    public AddCourseResult addCourse(@RequestBody CourseBase CourseBase) {
        return courseService.addCourse(CourseBase);
    }

    public static void main(String[] args) {
        System.out.println("Tue May 29 03:03:54 CST 2018");
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String formatDate = df.format(date);
        System.out.println(formatDate);
    }

    @Override
    @GetMapping("/courseview/{id}")
    public CourseView courseview(@PathVariable("id") String id) {
        return courseService.getCoruseView(id);
    }

    @Override
    @PostMapping("/preview/{id}")
    public CoursePublishResult preview(@PathVariable("id") String id) {
        return courseService.preview(id);
    }


    @Override
    @PostMapping("/publish/{id}")
    public CoursePublishResult publish(@PathVariable("id")String id) {
        return courseService.publish(id);
    }


}
