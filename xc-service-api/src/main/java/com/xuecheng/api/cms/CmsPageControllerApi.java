package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * Created by bobo on 2018/10/10.
 */
public interface CmsPageControllerApi {

    public QueryResponseResult findList(int page,int size,QueryPageRequest queryPageRequest);
}
