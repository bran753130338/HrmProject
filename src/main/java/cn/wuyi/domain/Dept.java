package cn.wuyi.domain;

import lombok.Data;

//部门实体类
@Data
public class Dept {
    private Integer deptId;//部门编号

    private String deptName;//部门名字

    private String deptRemark;//部门备注

}