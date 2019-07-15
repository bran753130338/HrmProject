package cn.wuyi.domain;

import lombok.Data;

//职位实体类
@Data
public class Job {
    private Integer jobId;//职位编号

    private String jobName;//职位名称

    private String jobRemark;//职位备注

}
