package cn.wuyi.dao;

import cn.wuyi.domain.Dept;
import cn.wuyi.domain.QueryResult;

import java.util.List;

public interface DeptDao {
    //查找所有的部门
    List<Dept> findAllDept();

    //添加部门
    int addDept(Dept dept);

    //判断部门是否早已存在
    boolean isExist(Dept dept);

    //判断部门有没有作为外键在员工表被引用
    boolean isExistInEmployee(Dept dept);

    //分页查询
    QueryResult pageQuery(int startindex, int pagesize);

    //更新操作
    int updateDept(Dept dept);

    //删除操作
    int deleteDept(Dept dept);
}
