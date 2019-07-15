package cn.wuyi.web;

import cn.wuyi.domain.Employee;
import cn.wuyi.service.EmployeeService;
import cn.wuyi.service.impl.EmployeeServiceImpl;
import cn.wuyi.utils.WebUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        Employee employee = WebUtils.request2Bean(request, Employee.class);
        EmployeeService employeeService = new EmployeeServiceImpl();
        int i = employeeService.updateEmployee(employee);
        String json = null;
        try{
            if(i>0){
                Employee e = employeeService.selectById(employee.getEmployId());
                //Gson识别Date格式是默认的，手动更改成自己想要的日期格式
                Gson gson = new Gson();
                JsonObject jsonObject = new JsonParser().parse(gson.toJson(e)).getAsJsonObject();
                String createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreateDate());
                String birthday = new SimpleDateFormat("yyyy-MM-dd").format(e.getBirthday());
                jsonObject.addProperty("birthday",birthday);
                jsonObject.addProperty("createDate",createDate);
                json = gson.toJson(jsonObject);
                response.getWriter().append(json);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().append(json);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
