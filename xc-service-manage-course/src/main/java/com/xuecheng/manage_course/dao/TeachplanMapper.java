package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bobo on 2018/12/27.
 */
@Mapper
public interface TeachplanMapper {

    public TeachplanNode selectList(@Param("courseId") String courseId);
}
