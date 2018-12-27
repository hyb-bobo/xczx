package com.xuecheng.manage_cms_client.service;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.manage_cms_client.dao.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bobo on 2018/12/26.
 */
@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    public CourseBase findCourseBaseById(String id){
        CourseBase courseBase = courseMapper.findCourseBaseById(id);
        return courseBase;
    }

}
