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
import java.util.Date;


@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        Employee employee = WebUtils.request2Bean(request, Employee.class);
        employee.setCreateDate(new Date());
        EmployeeService employeeService = new EmployeeServiceImpl();
        int i = employeeService.addEmployee(employee);
        response.getWriter().append(i+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
