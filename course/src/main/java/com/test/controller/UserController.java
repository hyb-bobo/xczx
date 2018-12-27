package com.test.controller;

import com.test.dao.CourseMapper;
import com.test.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bobo on 2018/12/27.
 */
@Controller
public class UserController {

    @Autowired
    private CourseMapper userMapper;
    @RequestMapping("/queryUser")
    @ResponseBody
    public List<User> queryUser(){
        List<User> users = userMapper.findCourseBaseById();
        return users;
    }
}
