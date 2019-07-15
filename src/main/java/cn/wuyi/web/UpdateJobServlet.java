package cn.wuyi.web;

import cn.wuyi.domain.Job;
import cn.wuyi.service.JobService;
import cn.wuyi.service.impl.JobServiceImpl;
import cn.wuyi.utils.WebUtils;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateJobServlet")
public class UpdateJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
//        Integer jobId = Integer.parseInt(request.getParameter("jobId"));
//        String jobName = request.getParameter("jobName");
//        String jobRemark = request.getParameter("jobRemark");
//        Job job = new Job();
//        job.setJobId(jobId);
//        job.setJobName(jobName);
//        job.setJobRemark(jobRemark);
        Job job = WebUtils.request2Bean(request, Job.class);
        JobService jobService = new JobServiceImpl();
        int i = jobService.updateJob(job);
        String json = null;
        if(i>0){
            Gson gson = new Gson();
            json = gson.toJson(job);
            response.getWriter().append(json);
        }else{
            response.getWriter().append(i+"");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
