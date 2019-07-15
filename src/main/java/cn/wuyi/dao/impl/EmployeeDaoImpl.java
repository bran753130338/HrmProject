package cn.wuyi.dao.impl;

import cn.wuyi.dao.EmployeeDao;
import cn.wuyi.domain.Employee;
import cn.wuyi.domain.QueryResult;
import cn.wuyi.utils.JDBCUtils;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public int addEmployee(Employee employee) {
        String sql = "insert into employee_inf" +
                    "(DEPT_ID,JOB_ID,NAME,CARD_ID,ADDRESS,POST_CODE,TEL,PHONE,QQ_NUM,EMAIL,SEX,PARTY,BIRTHDAY,RACE,EDUCATION,SPECIALITY,HOBBY,REMARK,CREATE_DATE) "+
                " value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String birthday = new SimpleDateFormat("yyyy-MM-dd").format(employee.getBirthday());
        String createDate = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(employee.getCreateDate());
        String[] info = {employee.getDeptId()+"",employee.getJobId()+"",employee.getEmployName(),employee.getCartId(),
                        employee.getAddress(),employee.getPostCode(),employee.getTel(),employee.getPhone(),
                        employee.getQqNum(),employee.getEmail(),employee.getSex()+"",employee.getParty(),
                        birthday,employee.getRace(),employee.getEducation(),employee.getSpeciality(),
                        employee.getHobby(),employee.getRemark(),createDate};
        int executeUpdate = JDBCUtils.executeUpdate(sql, info);
        return executeUpdate;
    }

    @Override
    public QueryResult pageQuery(int startindex, int pagesize) {
        ResultSet rs = null;
        QueryResult qr = new QueryResult();
        try {
            String sql = "select * from employee_inf limit " + startindex + "," + pagesize;
            rs = JDBCUtils.executeQuery(sql, null);
            List<Employee> list = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployId(rs.getInt(1));
                employee.setDeptId(rs.getInt(2));
                employee.setJobId(rs.getInt(3));
                employee.setEmployName(rs.getString(4));
                employee.setCartId(rs.getString(5));
                employee.setAddress(rs.getString(6));
                employee.setPostCode(rs.getString(7));
                employee.setTel(rs.getString(8));
                employee.setPhone(rs.getString(9));
                employee.setQqNum(rs.getString(10));
                employee.setEmail(rs.getString(11));
                employee.setSex(rs.getInt(12));
                employee.setParty(rs.getString(13));
                employee.setBirthday(rs.getDate(14));
                employee.setRace(rs.getString(15));
                employee.setEducation(rs.getString(16));
                employee.setSpeciality(rs.getString(17));
                employee.setHobby((rs.getString(18)));
                employee.setRemark(rs.getString(19));
                employee.setCreateDate(rs.getTimestamp(20));
                //将得到的对象放到集合中
                list.add(employee);
            }
            qr.setList(list);

            sql = "select count(*) from employee_inf";
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
    public int updateEmployee(Employee employee) {
        String sql = "update employee_inf set DEPT_ID=?,JOB_ID=?,NAME=?," +
                "CARD_ID=?,ADDRESS=?,POST_CODE=?,TEL=?,PHONE=?,QQ_NUM=?," +
                "EMAIL=?,SEX=?,PARTY=?,BIRTHDAY=?,RACE=?,EDUCATION=?,SPECIALITY=?," +
                "HOBBY=?,REMARK=? where ID = "+employee.getEmployId();
        String birthday = new SimpleDateFormat("yyyy-MM-dd").format(employee.getBirthday());
        String[] info = {employee.getDeptId()+"",employee.getJobId()+"",employee.getEmployName(),
                        employee.getCartId(),employee.getAddress(),employee.getPostCode(),
                        employee.getTel(),employee.getPhone(),employee.getQqNum(),employee.getEmail(),
                        employee.getSex()+"",employee.getParty(),birthday,employee.getRace(),employee.getEducation(),
                        employee.getSpeciality(),employee.getHobby(),employee.getRemark()};
        int executeUpdate = JDBCUtils.executeUpdate(sql, info);
        return executeUpdate;
    }

    @Override
    public Employee selectById(Integer id) {
        String sql = "select * from employee_inf where id = "+id;
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        try{
            if (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployId(resultSet.getInt(1));
                employee.setDeptId(resultSet.getInt(2));
                employee.setJobId(resultSet.getInt(3));
                employee.setEmployName(resultSet.getString(4));
                employee.setCartId(resultSet.getString(5));
                employee.setAddress(resultSet.getString(6));
                employee.setPostCode(resultSet.getString(7));
                employee.setTel(resultSet.getString(8));
                employee.setPhone(resultSet.getString(9));
                employee.setQqNum(resultSet.getString(10));
                employee.setEmail(resultSet.getString(11));
                employee.setSex(resultSet.getInt(12));
                employee.setParty(resultSet.getString(13));
                employee.setBirthday(resultSet.getDate(14));
                employee.setRace(resultSet.getString(15));
                employee.setEducation(resultSet.getString(16));
                employee.setSpeciality(resultSet.getString(17));
                employee.setHobby((resultSet.getString(18)));
                employee.setRemark(resultSet.getString(19));
                employee.setCreateDate(resultSet.getTimestamp(20));
                return  employee;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int deleteEmployee(Employee employee) {
        String sql = "delete from employee_inf where id ="+employee.getEmployId();
        int executeUpdate = JDBCUtils.executeUpdate(sql, null);
        return executeUpdate;
    }


}
