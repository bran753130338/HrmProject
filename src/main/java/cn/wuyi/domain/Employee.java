package cn.wuyi.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Employee {
    private Integer employId;//员工编号
    private Integer deptId;//所属部门编号
    private Integer jobId;//所属职位编号
    private String  employName;//员工姓名
    private String  cartId;//卡号
    private String  address;//地址
    private String  postCode;//邮政编码
    private String  tel;//联络电话
    private String  phone;//手机号码
    private String  qqNum;//QQ号码
    private String  email;//电子邮件地址
    private Integer sex;//性别
    private String  party;//政治面貌
    private Date    birthday;//出生年月
    private String  race;//民族
    private String  education;//学历
    private String  speciality;//专业
    private String  hobby;//爱好
    private String  remark;//备注
    private Date    createDate;//创建日期
}
