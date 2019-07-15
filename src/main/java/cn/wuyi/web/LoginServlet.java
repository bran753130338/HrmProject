package cn.wuyi.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wuyi.domain.User;
import cn.wuyi.service.UserService;
import cn.wuyi.service.impl.UserServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//1.接收从客户端发送过来的信息：通过name属性去获得值
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//2.将数据封装到javabean中
		User user = new User();
		user.setLoginname(username);
		user.setPassword(password);
        //3.调用service层的方法
		
		UserService us = new UserServiceImpl();
		int in = us.loginByLoginname(user);
		switch (in) {
		case 0:
			
			//向页面输出数据
			response.getWriter().append("<h1>没有这个用户</h1>");
			break;
		case 1:
			
			//向页面输出数据
			response.getWriter().append("<h1>对不起密码错误</h1>");
			break;
		case 2:
		    user = us.findByLoginName(user);
			request.setAttribute("user",user);
			//向页面输出数据
			request.getRequestDispatcher("main.jsp").forward(request,response);
			break;

		}
		System.out.println("用户名："+username+",密码："+password);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
