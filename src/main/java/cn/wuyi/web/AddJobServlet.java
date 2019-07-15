package cn.wuyi.web;

import cn.wuyi.domain.Job;
import cn.wuyi.service.JobService;
import cn.wuyi.service.impl.JobServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddJobServlet")
public class AddJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //以下两句代码处理中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String jobName = request.getParameter("jobName");
        String jobRemark = request.getParameter("jobRemark");

        Job job = new Job();
        job.setJobName(jobName);
        job.setJobRemark(jobRemark);

        JobService jobService = new JobServiceImpl();
        int i = jobService.addJob(job);
        response.getWriter().append(i+"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
