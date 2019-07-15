package cn.wuyi.dao.impl;

import cn.wuyi.dao.JobDao;
import cn.wuyi.domain.Job;
import cn.wuyi.domain.QueryResult;
import cn.wuyi.domain.User;
import cn.wuyi.utils.JDBCUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    @Override
    public List<Job> findAllJob() {
        String sql = "select * from job_inf";
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        List<Job> list = new ArrayList<>();
        try {
            while (resultSet.next()){
               Job job = new Job();
               job.setJobId(resultSet.getInt(1));
               job.setJobName(resultSet.getString(2));
               job.setJobRemark(resultSet.getString(3));
               list.add(job);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int addJob(Job job) {
        String sql = "insert into job_inf(name,remark) values(?,?)";
        String info[] = {job.getJobName(), job.getJobRemark()};
        int i = JDBCUtils.executeUpdate(sql, info);
        return i;
    }

    @Override
    public boolean isExist(Job job) {
        String sql = "select * from job_inf where name = ?";
        String info[] = {job.getJobName()};
        ResultSet resultSet = JDBCUtils.executeQuery(sql, info);
        try {
            if(resultSet.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistInEmployee(Job job) {
        String sql = "select * from employee_inf where job_id = "+job.getJobId();
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        try {
            if(resultSet.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public QueryResult pageQuery(int startindex, int pagesize) {
        ResultSet rs = null;
        QueryResult qr = new QueryResult();
        try {
            String sql = "select * from job_inf limit " + startindex + "," + pagesize;
            rs = JDBCUtils.executeQuery(sql, null);
            List<Job> list = new ArrayList<>();
            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt(1));
                job.setJobName(rs.getString(2));
                job.setJobRemark(rs.getString(3));
                //将得到的对象放到集合中
                list.add(job);
            }
            qr.setList(list);

            sql = "select count(*) from job_inf";
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


    //更新操作
    @Override
    public int updateJob(Job job) {
        String sql = "update job_inf set name = ? , remark = ? where id = " + job.getJobId();
        String[] info = {job.getJobName(),job.getJobRemark()};
        int executeUpdate = JDBCUtils.executeUpdate(sql, info);
        return executeUpdate;
    }

    @Override
    public int deleteJob(Job job) {
        String sql = "delete from job_inf where id = "+job.getJobId();
        int executeUpdate = JDBCUtils.executeUpdate(sql, null);
        return executeUpdate;
    }


}
