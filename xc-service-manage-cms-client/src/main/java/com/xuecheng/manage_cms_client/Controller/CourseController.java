package com.xuecheng.manage_cms_client.Controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms_client.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bobo on 2018/12/26.
 */
@Controller
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {

    @Autowired
    private CourseService courseService;

    @GetMapping("/findCourseBaseById/{id}")
    @ApiOperation(value = "查询课程")
    public Object findCourseBaseById(@PathVariable("id") String id){
        return courseService.findCourseBaseById("402885816240d276016240f7e5000002");
    }

    @Override
    public TeachplanNode findTeachplanList(String courseId) {
        return null;
    }

    @Override
    public ResponseResult addTeachplan(Teachplan teachplan) {
        return null;
    }

    @Override
    public QueryResponseResult findCourseList(int page, int size, CourseListRequest courseListRequest) {
        return null;
    }
}
