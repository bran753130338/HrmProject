package cn.wuyi.web;

import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;
import cn.wuyi.service.impl.DeptServiceImpl;
import cn.wuyi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SelectDeptServlet")
public class SelectDeptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try {
            QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
            DeptServiceImpl deptService = new DeptServiceImpl();
            PageBean pagebean = deptService.pageQuery(info);
            request.setAttribute("pagebean", pagebean);
            request.getRequestDispatcher("/selectdept.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
