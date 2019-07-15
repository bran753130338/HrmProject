package cn.wuyi.web;

import cn.wuyi.domain.User;
import cn.wuyi.service.UserService;
import cn.wuyi.service.impl.UserServiceImpl;
import cn.wuyi.utils.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        final String dateformat = "yyyy-MM-dd HH:mm:ss";
        User user = WebUtils.request2Bean(request, User.class);
        try {
            user.setCreatedate(new SimpleDateFormat(dateformat).parse(request.getParameter("createdate")) );
            System.out.println(user.getCreatedate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        UserService userService = new UserServiceImpl();
        int i = userService.UpdateUser(user);
        String json = null;
        try {
            if (i > 0) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                User userUpdate = userService.findByUserId(user.getId());
                json = gson.toJson(userUpdate);
                response.getWriter().append(json);
            } else {
                response.getWriter().append(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().append(json);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
