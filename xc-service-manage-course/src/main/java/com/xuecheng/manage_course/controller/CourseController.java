package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //添加课程计划
    @Override
    @PostMapping("/teachplan/add")
    public ResponseResult addTeachplan(@RequestBody Teachplan teachplan) {
        return courseService.addTeachplan(teachplan);
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
    public CourseBase getCoursebaseById(String courseid) {
        return courseService.getCoursebaseById(courseid);
    }


}
