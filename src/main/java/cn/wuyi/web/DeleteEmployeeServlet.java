package cn.wuyi.web;

import cn.wuyi.domain.Employee;
import cn.wuyi.service.EmployeeService;
import cn.wuyi.service.impl.EmployeeServiceImpl;
import cn.wuyi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Employee employee = WebUtils.request2Bean(request, Employee.class);
        EmployeeService employeeService = new EmployeeServiceImpl();
        int i = employeeService.deleteEmployee(employee);
        response.getWriter().append(i+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
