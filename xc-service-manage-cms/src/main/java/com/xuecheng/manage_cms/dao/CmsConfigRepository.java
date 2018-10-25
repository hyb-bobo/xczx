package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bobo on 2018/10/24.
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {
}
