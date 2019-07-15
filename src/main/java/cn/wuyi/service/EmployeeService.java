package cn.wuyi.service;

import cn.wuyi.domain.Employee;
import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;

public interface EmployeeService {

    //添加员工
    int addEmployee(Employee employee);

    //自写分页查找员工
    PageBean pageQuery(QueryInfo queryInfo);

    //更新员工信息
    int updateEmployee(Employee employee);

    //通过id查找员工
    Employee selectById(Integer id);

    //删除员工
    int deleteEmployee(Employee employee);
}
