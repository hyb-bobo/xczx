package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CategoryControllerApi;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bobo on 2019/1/3.
 */
@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerApi {



    @Override
    public CategoryNode findList() {

        return null;

    }
}
