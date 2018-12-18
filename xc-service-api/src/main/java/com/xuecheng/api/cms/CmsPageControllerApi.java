package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

/**
 * Created by bobo on 2018/10/10.
 */
public interface CmsPageControllerApi {

    @ApiOperation("查询列表页")
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    //添加页面
    @ApiOperation("添加页面")
    public CmsPageResult add(CmsPage cmsPage);

    @ApiOperation("通过ID查询页面")
    public CmsPage findById(String id);

    @ApiOperation("修改页面")
    public CmsPageResult edit(String id, CmsPage cmsPage);

    @ApiOperation("通过ID删除页面")
    public ResponseResult delete(String id);

    //页面发布
    @ApiOperation("页面发布")
    public ResponseResult post(String pageId);
}
