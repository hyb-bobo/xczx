package com.xuecheng.api.course;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xuecheng.framework.domain.course.ext.CategoryNode;

/**
 * Created by bobo on 2019/1/3.
 */
@Api(value = "课程分类管理", description = "课程分类管理", tags = {"课程分类管理"})
public interface CategoryControllerApi {

    @ApiOperation("查询分类")
    public CategoryNode findList();

}
