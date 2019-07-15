package cn.wuyi.service.impl;

import cn.wuyi.dao.EmployeeDao;
import cn.wuyi.dao.impl.EmployeeDaoImpl;
import cn.wuyi.domain.Employee;
import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;
import cn.wuyi.domain.QueryResult;
import cn.wuyi.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public int addEmployee(Employee employee) {

        return employeeDao.addEmployee(employee);
    }

    @Override
    public PageBean pageQuery(QueryInfo queryInfo) {
        // 调用dao获取到页面数据
        QueryResult qr = employeeDao.pageQuery(queryInfo.getStartindex(), queryInfo.getPagesize());

        // 根据dao查询结果，生成页面显示需要pagebean
        PageBean bean = new PageBean();
        bean.setCurrentpage(queryInfo.getCurrentpage());
        bean.setList(qr.getList());
        bean.setPagesize(queryInfo.getPagesize());
        bean.setTotalrecord(qr.getTotalrecord());
        return bean;
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public Employee selectById(Integer id) {
        return employeeDao.selectById(id);
    }

    @Override
    public int deleteEmployee(Employee employee) {
        return employeeDao.deleteEmployee(employee);
    }
}
