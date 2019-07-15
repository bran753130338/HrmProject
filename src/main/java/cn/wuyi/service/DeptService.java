package cn.wuyi.service;

import cn.wuyi.domain.Dept;
import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;

public interface DeptService {

    //添加部门
    int addDept(Dept dept);

    //分页查询用户
    PageBean pageQuery(QueryInfo queryInfo);

    //更新部门操作
    int updateDept(Dept dept);

    //删除部门操作
    int deleteDept(Dept dept);

}
