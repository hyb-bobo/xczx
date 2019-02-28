package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bobo on 2019/2/28.
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}
