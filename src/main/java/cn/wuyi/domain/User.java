package cn.wuyi.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 这是一个javabean类：
 * 
 * 什么是javaBean类：
 * 
 * 1.javabean类就是一个标准的java类
 * 
 * 2.必须实现序列化接口（开发中很多时候偷懒省略了）
 * 
 * 3.有get()和set方法(不能偷懒)
 * 
 * 4.有无参构造器（开发中很多时候偷懒省略了）
 * @author newuser
 *
 */
@Data
public class User implements Serializable {
	private int id;//id
	private String loginname;//登录名
	private String password;//密码
	private int status;//状态
	private Date createdate;//创建时间
	private String username ;//用户名
}
