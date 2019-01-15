package com.xuecheng.manage_course.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.AddCourseResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.utils.DateUtil;
import com.xuecheng.manage_course.dao.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by bobo on 2018/12/27.
 */
@Service
public class CourseService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    CourseBaseRepository courseBaseRepository;

    @Autowired
    TeachplanRepository teachplanRepository;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseMarketMapper courseMarketMapper;

    //查询课程计划
    public TeachplanNode findTeachplanList(String courseId) {
        TeachplanNode teachplanNode = teachplanMapper.selectList(courseId);
        return teachplanNode;
    }

    //获取课程根结点，如果没有则添加根结点
    public String getTeachplanRoot(String courseId) {
        //校验课程id
        Optional<CourseBase> optional = courseBaseRepository.findById(courseId);
        if (!optional.isPresent()) {
            return null;
        }
        CourseBase courseBase = optional.get();
        //取出课程计划根结点
        List<Teachplan> teachplanList = teachplanRepository.findByCourseidAndParentid(courseId, "0");
        if (teachplanList == null || teachplanList.size() == 0) {
            //新增一个根结点
            Teachplan teachplanRoot = new Teachplan();
            teachplanRoot.setCourseid(courseId);
            teachplanRoot.setPname(courseBase.getName());
            teachplanRoot.setParentid("0");
            teachplanRoot.setGrade("1");//1级
            teachplanRoot.setStatus("0");//未发布
            teachplanRepository.save(teachplanRoot);
            return teachplanRoot.getId();
        }
        Teachplan teachplan = teachplanList.get(0);
        return teachplan.getId();
    }

    //添加课程计划
    @Transactional
    public ResponseResult addTeachplan(Teachplan teachplan) {
        //校验课程id和课程计划名称
        if (teachplan == null ||
                StringUtils.isEmpty(teachplan.getCourseid()) ||
                StringUtils.isEmpty(teachplan.getPname())) {
            ExceptionCast.cast(CommonCode.INVALIDPARAM);
        }
        //取出课程id
        String courseid = teachplan.getCourseid();
        //取出父结点id
        String parentid = teachplan.getParentid();
        if (StringUtils.isEmpty(parentid)) {
            //如果父结点为空则获取根结点
            parentid = getTeachplanRoot(courseid);
        }
        //取出父结点信息
        Optional<Teachplan> teachplanOptional = teachplanRepository.findById(parentid);
        if (!teachplanOptional.isPresent()) {
            ExceptionCast.cast(CommonCode.INVALIDPARAM);
        }
        //父结点
        Teachplan teachplanParent = teachplanOptional.get();
        //父结点级别
        String parentGrade = teachplanParent.getGrade();
        //设置父结点
        teachplan.setParentid(parentid);
        teachplan.setStatus("0");//未发布
        //子结点的级别，根据父结点来判断
        if (parentGrade.equals("1")) {
            teachplan.setGrade("2");
        } else if (parentGrade.equals("2")) {
            teachplan.setGrade("3");
        }
        //设置课程id
        teachplan.setCourseid(teachplanParent.getCourseid());
        teachplanRepository.save(teachplan);
        return new ResponseResult(CommonCode.SUCCESS);
    }


    public QueryResponseResult findCourseList(int page, int size, CourseListRequest courseListRequest) {
        PageHelper.startPage(page, size);//查询第一页，每页显示10条记录
        Page<CourseInfo> courseListPage = courseMapper.findCourseListPage(courseListRequest);
//        List<CourseInfo> result = courseListPage.getResult();
        QueryResult<CourseInfo> courseInfoResult = new QueryResult<CourseInfo>();
        courseInfoResult.setList(courseListPage.getResult());
        courseInfoResult.setTotal(courseListPage.getTotal());
        QueryResponseResult QueryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, courseInfoResult);
        return QueryResponseResult;
    }

    public CourseBase getCoursebaseById(String courseid) {
        CourseBase courseBaseById = courseMapper.findCourseBaseById(courseid);
        return courseBaseById;
    }

    public AddCourseResult addCourse(CourseBase courseBase) {
        /**
         * 这种保存方式 是完全依托前端进行数据格式的校验和保证 并且没有任何机会到后台进行数据的处理
         * 这种方式是不建议的
         * 至少需要一个中间的model进行参数的转接  考虑到后台数据的处理以及必要的异常处理
         */
        //课程状态默认为未发布
        courseBase.setStatus("202001");
        courseBaseRepository.save(courseBase);
        return new AddCourseResult(CommonCode.SUCCESS, courseBase.getId());
    }

    public CourseMarket getCourseMarketById(String courseId) {
        CourseMarket courseMarket = courseMarketMapper.getCourseMarketById(courseId);
        return courseMarket;
    }

    public ResponseResult updateCoursebase(CourseBase courseBase) {
        CourseBase save = courseBaseRepository.save(courseBase);

        return new ResponseResult(CommonCode.SUCCESS);

    }

    public ResponseResult updateCourseMarket(CourseMarket courseMarket) {
        if (courseMarket.getStartTime() != null) {
            courseMarket.setStartTime(DateUtil.date2View(courseMarket.getStartTime()));
        }
        if (courseMarket.getEndTime() != null) {
            courseMarket.setEndTime(DateUtil.date2View(courseMarket.getEndTime()));
        }
        int i = courseMarketMapper.updateCourseMarket(courseMarket);
        System.out.println(i);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        } else {
            return new ResponseResult(CommonCode.FAIL);
        }
    }


}
