package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CourseMarket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bobo on 2019/1/14.
 */
@Mapper
public interface CourseMarketMapper {

    CourseMarket getCourseMarketById(@Param("courseId") String courseId);

    int updateCourseMarket(CourseMarket courseMarket);
}
