package cn.wuyi.web;

import cn.wuyi.service.AnnoService;
import cn.wuyi.service.impl.AnnoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SelectAnnoServlet")
public class SelectAnnoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        //easyui的分页查找功能，会自动发送pages和rows两个参数用于sql分页查找的 limit pages，rows
        //注意：easyui发送的page的值默认为1，而sql查找是从0开始
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));
        AnnoService annoService = new AnnoServiceImpl();
        String s = annoService.selectAnno(page-1, rows);
        response.getWriter().append(s);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
