package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by bobo on 2018/10/10.
 */
@Data
public class QueryPageRequest extends RequestData {

    //站点id
    @ApiModelProperty("站点id")
    private String siteId;
    //页面ID
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模版id
    private String templateId;
    //页面类型
    private String pageType;

}
