package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import io.swagger.annotations.ApiOperation;

/**
 * Created by bobo on 2018/12/25.
 */
public interface CourseControllerApi {

    @ApiOperation("课程计划查询")
    public TeachplanNode findTeachplanList(String courseId);
}
