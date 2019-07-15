package cn.wuyi.service.impl;

import cn.wuyi.dao.DeptDao;
import cn.wuyi.dao.impl.DeptDaoImpl;
import cn.wuyi.domain.Dept;
import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;
import cn.wuyi.domain.QueryResult;
import cn.wuyi.service.DeptService;
import com.google.gson.Gson;

import java.util.List;

public class DeptServiceImpl implements DeptService {
    private DeptDao deptDao = new DeptDaoImpl();
    @Override
    public int addDept(Dept dept) {

        boolean deptExist = deptDao.isExist(dept);
        if(deptExist){
            return 2;
        }
        return deptDao.addDept(dept);
    }

    @Override
    public PageBean pageQuery(QueryInfo queryInfo) {

        // 调用dao获取到页面数据
        QueryResult qr = deptDao.pageQuery(queryInfo.getStartindex(), queryInfo.getPagesize());

        // 根据dao查询结果，生成页面显示需要pagebean
        PageBean bean = new PageBean();
        bean.setCurrentpage(queryInfo.getCurrentpage());
        bean.setList(qr.getList());
        bean.setPagesize(queryInfo.getPagesize());
        bean.setTotalrecord(qr.getTotalrecord());

        return bean;
    }


    @Override
    public int updateDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public int deleteDept(Dept dept) {
        boolean existInEmployee = deptDao.isExistInEmployee(dept);
        if (existInEmployee){
            return  2;
        }
        return deptDao.deleteDept(dept);
    }
}
