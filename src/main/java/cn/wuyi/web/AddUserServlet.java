package cn.wuyi.web;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.wuyi.domain.User;
import cn.wuyi.service.UserService;
import cn.wuyi.service.impl.UserServiceImpl;

/**
 * 添加用户的servlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//以下两句代码处理中文乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取客户端发送过来的用户信息
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		String loginname = request.getParameter("loginName");
		//将得到的数据封装到对象中
		User user = new User();
		user.setUsername(username);
		user.setLoginname(loginname);
		user.setPassword(password);
		//int类型
		user.setStatus(Integer.parseInt(status));
		user.setCreatedate(new Date());
		//调用Servlet层的添加方法
		UserService us = new UserServiceImpl();
		int rs = us.addUser(user);
		if(rs!=0) {
			//往页面放回数据
			response.getWriter().append("1");
		}else {
			response.getWriter().append("0");
		}
		
		System.out.println("用户名是："+username);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
