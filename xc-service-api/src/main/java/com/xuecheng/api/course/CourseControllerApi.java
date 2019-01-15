package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.AddCourseResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by bobo on 2018/12/25.
 */
@Api(value = "课程计划接口", description = "课程计划接口")
public interface CourseControllerApi {

    @ApiOperation("课程计划查询")
    public TeachplanNode findTeachplanList(String courseId);

    @ApiOperation("课程营销查询")
    public CourseMarket getCourseMarketById(String courseId);

    @ApiOperation("添加课程计划")
    public ResponseResult addTeachplan(Teachplan teachplan);

    @ApiOperation("更新课程信息")
    public ResponseResult updateCoursebase(CourseBase Course);

    @ApiOperation("更新营销信息")
    public ResponseResult updateCourseMarket(CourseMarket courseMarket);

    @ApiOperation("添加课程基础信息")
    public AddCourseResult addCourse(CourseBase Course);

//    //查询课程列表
//    @ApiOperation("查询我的课程列表")
//    public QueryResponseResult<CourseInfo> findCourseList(
//            int page,
//            int size,
//            CourseListRequest courseListRequest
//    );

    //查询课程列表
    @ApiOperation("查询我的课程列表")
    public QueryResponseResult findCourseList(int page, int size, CourseListRequest courseListRequest);

    //查询课程列表
    @ApiOperation("根据id查询课程信息")
    public CourseBase getCoursebaseById(String courseid);
}
