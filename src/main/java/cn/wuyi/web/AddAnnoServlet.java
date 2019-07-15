package cn.wuyi.web;

import cn.wuyi.domain.Anno;
import cn.wuyi.service.AnnoService;
import cn.wuyi.service.impl.AnnoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/AddAnnoServlet")
public class AddAnnoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Anno anno = new Anno();
        anno.setTitle(title);
        anno.setContent(content);
        anno.setDate(new Date());
        AnnoService annoService = new AnnoServiceImpl();
        int i = annoService.addAnno(anno);
        response.getWriter().append(i+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
