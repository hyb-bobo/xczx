package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bobo on 2018/10/10.
 */
@RestController
@RequestMapping("/cms/page")
@Api(value = "cms页面管理接口", description = "cms页面管理接口，提供页面的增、删、改、查")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    PageService pageService;

//    @ApiOperation(value = "校验计划任务设备重复性选择")
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "eupIds", value = "eupIds", dataType = "String", paramType = "query")
    })*/
    @ApiOperation(value = "查询列表")
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        //暂时采用测试数据，测试接口是否可以正常运行
/*        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(2);
        //静态数据列表
        List list = new ArrayList();
        CmsPage cmsPage= new CmsPage();
        cmsPage.setPageName("测试页面");
        list.add(cmsPage);
        queryResult.setList(list);
        QueryResponseResult queryResponseResult = new  QueryResponseResult(CommonCode.SUCCESS,queryResult);*/
//        return queryResponseResult;
        return pageService.findList(page, size, queryPageRequest);
    }

    //添加页面
    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }

    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        return pageService.findById(id);
    }

    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return pageService.edit(id, cmsPage);
    }

    @DeleteMapping("/del/{id}")  //使用http的delete方法完成岗位操作
    public ResponseResult delete(@PathVariable("id")String id) {
        return pageService.delete(id);
    }

}
