package cn.wuyi.web;

import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;
import cn.wuyi.service.EmployeeService;
import cn.wuyi.service.impl.EmployeeServiceImpl;
import cn.wuyi.utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SelectEmployeeServlet")
public class SelectEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
            EmployeeService employeeService = new EmployeeServiceImpl();
            PageBean pagebean = employeeService.pageQuery(info);
            request.setAttribute("pagebean", pagebean);
            //System.out.println(pagebean.getList());
            //转发到用户信息显示页面
            request.getRequestDispatcher("/selectemployee.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查看客户失败！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
