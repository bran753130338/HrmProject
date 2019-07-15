package cn.wuyi.service.impl;

import cn.wuyi.dao.JobDao;
import cn.wuyi.dao.impl.JobDaoImpl;
import cn.wuyi.domain.Job;
import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;
import cn.wuyi.domain.QueryResult;
import cn.wuyi.service.JobService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class JobServiceImpl implements JobService {

    private JobDao jobDao = new JobDaoImpl();

    @Override
    public int addJob(Job job) {
        boolean jobExist = jobDao.isExist(job);
        if(jobExist){
            return 2;
        }
        return jobDao.addJob(job);
    }

    @Override
    public PageBean pageQuery(QueryInfo queryInfo) {
        // 调用dao获取到页面数据
        QueryResult qr = jobDao.pageQuery(queryInfo.getStartindex(), queryInfo.getPagesize());

        // 根据dao查询结果，生成页面显示需要pagebean
        PageBean bean = new PageBean();
        bean.setCurrentpage(queryInfo.getCurrentpage());
        bean.setList(qr.getList());
        bean.setPagesize(queryInfo.getPagesize());
        bean.setTotalrecord(qr.getTotalrecord());

        return bean;

    }


    @Override
    public int updateJob(Job job) {
        return jobDao.updateJob(job);
    }

    @Override
    public int deleteJob(Job job) {
        boolean existInEmployee = jobDao.isExistInEmployee(job);
        if(existInEmployee){
            return 2;
        }
        return jobDao.deleteJob(job);
    }
}
