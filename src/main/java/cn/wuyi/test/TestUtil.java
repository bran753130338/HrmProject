package cn.wuyi.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.wuyi.utils.JDBCUtils;

public class TestUtil {

	public static void main(String[] args) {
		// 新增用户
		String sql = "insert into user_inf(loginname,PASSWORD,STATUS,createdate,username) values(?,?,?,?,?);";
		//将当前时间转换成指定的格式
		String createTiem = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String info[] = {"test3","123","1",createTiem,"李四"};
		JDBCUtils.executeUpdate(sql, info);
		
		
	}

	// 查询用户
	private static void findUserbyLoginname() {
		String sql = "select * from user";
		ResultSet rs = JDBCUtils.executeQuery(sql, null);
		try {
			while (rs.next()) {
				String id = rs.getString("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				// 将得到的数据往控制台输出
				System.out.println("id==" + id + ",username==" + username + ",password==" + password);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(JDBCUtils.getCt(), JDBCUtils.getPs(), JDBCUtils.getRs());
		}
	}
}
