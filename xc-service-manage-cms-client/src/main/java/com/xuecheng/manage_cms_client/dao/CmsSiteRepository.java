package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bobo on 2018/12/18.
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}
