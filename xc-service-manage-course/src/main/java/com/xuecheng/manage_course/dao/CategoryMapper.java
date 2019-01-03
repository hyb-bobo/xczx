package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by bobo on 2019/1/3.
 */
@Mapper
public interface CategoryMapper {

    public CategoryNode findList();
}
