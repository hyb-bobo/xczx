package com.xuecheng.api.search;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xuecheng.framework.domain.search.CourseSearchParam;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * Created by Administrator.
 */
@Api(value = "课程搜索",description = "课程搜索",tags = {"课程搜索"})
public interface EsCourseControllerApi {
    //搜索课程信息
    @ApiOperation("课程综合搜索")
    public QueryResponseResult list(int page, int size, CourseSearchParam courseSearchParam);
}
