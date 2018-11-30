package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bobo on 2018/11/29.
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {
}
