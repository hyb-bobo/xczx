package com.xuecheng.api.cms;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xuecheng.framework.domain.cms.CmsConfig;

/**
 * Created by bobo on 2018/10/24.
 */
@Api(value="cms配置管理接口",description = "cms配置管理接口，提供数据模型的管理、查询接口")
public interface CmsConfigControllerApi {

    @ApiOperation("根据id查询CMS配置信息")
    public CmsConfig getmodel(String id);
}
