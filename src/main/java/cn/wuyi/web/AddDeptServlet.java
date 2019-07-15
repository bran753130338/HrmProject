package cn.wuyi.web;

import cn.wuyi.domain.Dept;
import cn.wuyi.service.DeptService;
import cn.wuyi.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddDeptServlet")
public class AddDeptServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //以下两句代码处理中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String deptName =request.getParameter("deptName");
        String deptRemark = request.getParameter("deptRemark");
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        dept.setDeptRemark(deptRemark);
        DeptService deptService = new DeptServiceImpl();
        int i = deptService.addDept(dept);
        response.getWriter().append(i+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
