package cn.wuyi.dao;


import cn.wuyi.domain.Job;
import cn.wuyi.domain.QueryResult;

import java.util.List;

public interface JobDao {

    //查找所有的职位
    List<Job> findAllJob();

    //添加职位
    int addJob(Job job);

    //判断职位是否已存在
    boolean isExist(Job job);

    //判断该职位有没有作为外键
    boolean isExistInEmployee(Job job);

    //分页查询
    QueryResult pageQuery(int startindex, int pagesize);

    //更新职位信息
    int updateJob(Job job);

    //删除该职位
    int deleteJob(Job job);


}
