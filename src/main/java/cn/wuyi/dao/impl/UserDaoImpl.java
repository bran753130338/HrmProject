package cn.wuyi.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import cn.wuyi.dao.UserDao;
import cn.wuyi.domain.QueryResult;
import cn.wuyi.domain.User;
import cn.wuyi.utils.JDBCUtils;


/**
 * 操作数据库
 * 
 * @author newuser
 *
 */
public class UserDaoImpl implements UserDao {

	@Override
	public User findByLoginname(User user) {
		String sql = "select * from user_inf where loginname=?";
		String info[] = { user.getLoginname() };
		// 查询数据
		ResultSet rs = JDBCUtils.executeQuery(sql, info);
		// 将查询到的数据封装到javabean，并返回
		try {
			if (rs.next()) {
				// 创建一个对象
				User user_rs = new User();
				// 将查询到的数据封装到对象中
				user_rs.setId(rs.getInt("id"));
				user_rs.setLoginname(rs.getString("loginname"));
				user_rs.setPassword(rs.getString("password"));
				user_rs.setStatus(rs.getInt("status"));
				user_rs.setCreatedate(rs.getTimestamp("createdate"));
				user_rs.setUsername(rs.getString("username"));
				System.out.println(user_rs);
				return user_rs;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(JDBCUtils.getCt(), JDBCUtils.getPs(), JDBCUtils.getRs());
		}

		return null;
	}

    @Override
    public User findByUserId(Integer id) {
	    String sql = "select * from user_inf where id = " + id;
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        try {
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setLoginname(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setStatus(resultSet.getInt(4));
                user.setCreatedate(resultSet.getTimestamp(5));
                user.setUsername(resultSet.getString(6));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    // 添加用户
	@Override
	public int addUser(User user) {

		// 新增用户
		String sql = "insert into user_inf(loginname,PASSWORD,STATUS,createdate,username) values(?,?,?,?,?);";
		// 将当前时间转换成指定的格式
		String createTiem = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreatedate());
		String info[] = { user.getLoginname(), user.getPassword(), user.getStatus() + "", createTiem,
				user.getUsername() };
		int rs = JDBCUtils.executeUpdate(sql, info);

		// 返回操作的结果
		return rs;
	}

	// 3.用户分页查询
	@SuppressWarnings("resource")
	public QueryResult pageQuery(int startindex, int pagesize) {
		ResultSet rs = null;
		QueryResult qr = new QueryResult();
		try {
			String sql = "select * from user_inf limit " + startindex + "," + pagesize;
			rs = JDBCUtils.executeQuery(sql, null);
			List<User> list = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginname(rs.getString("loginname"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setCreatedate(rs.getTimestamp("createdate"));
				user.setStatus(rs.getInt("status"));
	
				//将得到的对象放到集合中
				list.add(user);
			}
			qr.setList(list);

			sql = "select count(*) from user_inf";
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
    public int updateUser(User user) {
        String createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreatedate());
        String sql = "update user_inf set username = ? , " +
                "loginname = ? , password = ? , status = "+user.getStatus() +", createdate = ?"+
                " where id = "+user.getId();
	    String[] info = {user.getUsername(),user.getLoginname(),user.getPassword(),createDate};
        int executeUpdate = JDBCUtils.executeUpdate(sql, info);
        return executeUpdate;
    }

    @Override
    public int deleteUser(User user) {
	    String sql = "delete from user_inf where id = "+user.getId();
        int executeUpdate = JDBCUtils.executeUpdate(sql, null);
        return executeUpdate;
    }
}
