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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/UpdateAnnoServlet")
public class UpdateAnnoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        Integer id = Integer.parseInt(request.getParameter("id")) ;
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Anno anno = new Anno();
        anno.setId(id);
        anno.setTitle(title);
        anno.setContent(content);
        anno.setDate(date);
        AnnoService annoService = new AnnoServiceImpl();
        int i = annoService.updateAnno(anno);
        response.getWriter().append(i+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
