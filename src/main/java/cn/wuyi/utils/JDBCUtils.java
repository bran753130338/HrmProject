package cn.wuyi.utils;
/**
 * 这是一个JDBC自己封装的工具类，
 * 方便以后开发
 * @author newuser
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
	// 定义出工具类所要用到的变量
	private static final String url = "jdbc:mysql://localhost:3306/wuyi?useUnicode=true&characterEncoding=utf-8";
	private static final String username = "root";
	private static final String password = "root";
	private static final String driver = "com.mysql.jdbc.Driver";

	private static Connection ct = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	// 1.加载驱动，注意整个项目只用加载一次，所以，可以将加载驱动的代码放到静态代码块中
	// 静态代码块：在类加载的时候运行，而且只运行一次，不管创建多少个对象都只运行一次
	static {

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2. 编写一个方法，可以返回链接对象
	public static Connection getConnection() {
		// 参数：1.数据库url地址：固定的,2.数据库的用户名，3.数据库的密码
		try {
			ct = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}

	// 3.封装一个方法，可以完成增删改的操作
	public static int executeUpdate(String sql, String[] info) {
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);

			// 以下，处理占位符：
			/*
			 * 占位符注意事项： 1.序号是从1开始 2.有几个？就要用几个占位符来替换
			 * 
			 */
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					ps.setString(i + 1, info[i]);
				}

			}
			// 4.发送sql语句:有两种：查询语句，增，删，改语句
			int rs = ps.executeUpdate();
			// 5.得到返回结果
			if (rs > 0) {
				System.out.println("添加成功！作用了" + rs + "条记录！");
				return rs;
			} else {
				System.out.println("操作失败");
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// finally里面的代码，不管是否发生异常，都会进去执行，一般都会将资源在finally关闭

			close(ct, ps, rs);
			
		}

		return 0;
	}

	// 3.封装一个方法，可以完成查询的操作
	public static ResultSet executeQuery(String sql, String[] info) {
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);

			// 以下，处理占位符：
			/*
			 * 占位符注意事项： 1.序号是从1开始 2.有几个？就要用几个占位符来替换
			 * 
			 */
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					ps.setString(i + 1, info[i]);
				}

			}
			rs = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}
	//编写一个方法关闭流
	public static void close(Connection ct,PreparedStatement ps,ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}
			if (null != ct) {
				ct.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
	}
	
	//编写三个方法，可以放回三个对象
	public static Connection getCt() {
		return ct;
	}
	public static PreparedStatement getPs() {
		return ps;
	}
	public static ResultSet getRs() {
		return rs;
	}
}
