package com.test.dao;

import com.test.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator.
 */
@Mapper
public interface CourseMapper {
    List<User> findCourseBaseById();
}
