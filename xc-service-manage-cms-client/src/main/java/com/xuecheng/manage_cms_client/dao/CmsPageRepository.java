package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bobo on 2018/12/18.
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
}
