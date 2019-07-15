package cn.wuyi.dao.impl;

import cn.wuyi.dao.DeptDao;
import cn.wuyi.domain.Dept;
import cn.wuyi.domain.Job;
import cn.wuyi.domain.QueryResult;
import cn.wuyi.utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl implements DeptDao {
    @Override
    public List<Dept> findAllDept() {
        String sql = "select * from dept_inf";
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        List<Dept> list = new ArrayList<>();
        try {
            while (resultSet.next()){
                Dept dept = new Dept();
                dept.setDeptId(resultSet.getInt(1));
                dept.setDeptName(resultSet.getString(2));
                dept.setDeptRemark(resultSet.getString(3));
                list.add(dept);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public int addDept(Dept dept) {
        String sql = "insert into dept_inf(name,remark) values(?,?)";
        String[] info = {dept.getDeptName(), dept.getDeptRemark()};
        int executeUpdate = JDBCUtils.executeUpdate(sql, info);
        return executeUpdate;
    }

    @Override
    public boolean isExist(Dept dept) {
        String sql = "select * from dept_inf where name = ?";
        String[] info = {dept.getDeptName()};
        ResultSet resultSet = JDBCUtils.executeQuery(sql, info);
        try {
            if (resultSet.next()) {
                //说明该部门已经存在
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistInEmployee(Dept dept) {
        String sql = "select * from employee_inf where dept_id = " + dept.getDeptId();
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public QueryResult pageQuery(int startindex, int pagesize) {
        ResultSet rs = null;
        QueryResult qr = new QueryResult();
        try {
            String sql = "select * from dept_inf limit " + startindex + "," + pagesize;
            rs = JDBCUtils.executeQuery(sql, null);
            List<Dept> list = new ArrayList<>();
            while (rs.next()) {
                Dept dept = new Dept();
                dept.setDeptId(rs.getInt(1));
                dept.setDeptName(rs.getString(2));
                dept.setDeptRemark(rs.getString(3));
                //将得到的对象放到集合中
                list.add(dept);
            }
            qr.setList(list);

            sql = "select count(*) from dept_inf";
            rs = JDBCUtils.executeQuery(sql, null);
            if (rs.next()) {
                int totalrecord = rs.getInt(1);
                qr.setTotalrecord(totalrecord);
                System.out.println("总记录数：" + totalrecord + "条");
            }
            return qr;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(JDBCUtils.getCt(), JDBCUtils.getPs(), rs);
        }
        return null;
    }


    @Override
    public int updateDept(Dept dept) {
        String sql = "update dept_inf set name = ? , remark = ? where id = " + dept.getDeptId();
        String[] info = {dept.getDeptName(), dept.getDeptRemark()};
        int executeUpdate = JDBCUtils.executeUpdate(sql, info);
        return executeUpdate;
    }

    @Override
    public int deleteDept(Dept dept) {
        String sql = "delete from dept_inf where id = " + dept.getDeptId();
        int executeUpdate = JDBCUtils.executeUpdate(sql, null);
        return executeUpdate;
    }
}
