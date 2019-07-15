package cn.wuyi.service;

import cn.wuyi.domain.Job;
import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;

import java.util.List;

public interface JobService {

    int addJob(Job job);
    //3.分页查询用户
    PageBean pageQuery(QueryInfo queryInfo);

    int updateJob(Job job);

    int deleteJob(Job job);
}
