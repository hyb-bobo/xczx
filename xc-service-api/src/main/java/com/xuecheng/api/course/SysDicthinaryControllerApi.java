package com.xuecheng.api.course;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xuecheng.framework.domain.system.SysDictionary;

/**
 * Created by bobo on 2019/1/3.
 */
@Api(value = "数据字典接口", description = "提供数据字典接口的管理、查询功能")
public interface SysDicthinaryControllerApi {

    //数据字典
    @ApiOperation(value = "数据字典查询接口")
    public SysDictionary getByType(String type);
}
