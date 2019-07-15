package cn.wuyi.web;

import cn.wuyi.domain.Dept;
import cn.wuyi.service.DeptService;
import cn.wuyi.service.impl.DeptServiceImpl;
import cn.wuyi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteDeptServlet")
public class DeleteDeptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Dept dept = WebUtils.request2Bean(request, Dept.class);
        DeptService deptService = new DeptServiceImpl();
        int i = deptService.deleteDept(dept);
        response.getWriter().append(i+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
