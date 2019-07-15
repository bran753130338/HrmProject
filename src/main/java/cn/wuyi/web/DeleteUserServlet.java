package cn.wuyi.web;

import cn.wuyi.domain.User;
import cn.wuyi.service.UserService;
import cn.wuyi.service.impl.UserServiceImpl;
import cn.wuyi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = WebUtils.request2Bean(request, User.class);
        UserService userService = new UserServiceImpl();
        int i = userService.deleteUser(user);
        response.getWriter().append(i+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
