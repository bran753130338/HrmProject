package cn.wuyi.dao;

import cn.wuyi.domain.Employee;
import cn.wuyi.domain.QueryResult;

public interface EmployeeDao {

    int addEmployee(Employee employee);

    //分页查询员工表
    QueryResult pageQuery(int startindex, int pagesize);

    int updateEmployee(Employee employee);

    Employee selectById(Integer id);

    int deleteEmployee(Employee employee);
}
