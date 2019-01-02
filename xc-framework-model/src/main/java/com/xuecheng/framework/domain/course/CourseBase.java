package com.xuecheng.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@Entity
@Table(name="course_base")
//@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CourseBase implements Serializable {
    private static final long serialVersionUID = -916357110051689486L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
//    @Column(name="name")
    private String name;
//    @Column(name="users")
    private String users;
//    @Column(name="mt")
    private String mt;
//    @Column(name="st")
    private String st;
//    @Column(name="grade")
    private String grade;
//    @Column(name="studymodel")
    private String studymodel;
//    @Column(name="teachmode")
    private String teachmode;
//    @Column(name="description")
    private String description;
//    @Column(name="status")
    private String status;
//    @Column(name="company_id")
    private String companyId;
//    @Column(name="user_id")
    private String userId;
    /**
     * todo
     * 就是 列名和数据库字段对应问题
     * 回显和保存的时候 是否会存在冲突
     */

}
