package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.manage_course.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bobo on 2019/1/3.
 */
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    //查询分类
    public CategoryNode findList() {
        return categoryMapper.findList();
    }
}
