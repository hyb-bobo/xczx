package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CourseMarket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bobo on 2019/2/19.
 */
public interface CourseMarketRepository  extends JpaRepository<CourseMarket,String> {
}
