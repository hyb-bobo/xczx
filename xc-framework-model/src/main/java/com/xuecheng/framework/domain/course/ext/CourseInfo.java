package com.xuecheng.framework.domain.course.ext;

import com.xuecheng.framework.domain.course.CourseBase;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
public class CourseInfo extends CourseBase {

    //课程图片
    private String pic;
    private String id;
    private String name;
    private String users;
    private String mt;
    private String grade;
    private String studymodel;
    private String teachmode;
    private String description;
    private String st;
    private String status;
    @Column(name="company_id")
    private String companyId;
    @Column(name="user_id")
    private String userId;

}
