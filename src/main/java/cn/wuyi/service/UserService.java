package cn.wuyi.service;


import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;
import cn.wuyi.domain.User;

/**
 * userservice的接口
 * 
 * 接口不能实例化，必须通过实行类
 * @author newuser
 *
 */
public interface UserService {
	//1.一个抽象方法，完成用户登录判断：return int : 0-->没有这个用户，1--->密码错误，2---->登录成功
	int loginByLoginname(User user);
	
	//2.注册用户的方法
	int addUser(User user);
	//3.分页查询用户
    PageBean pageQuery(QueryInfo queryInfo);

    int UpdateUser(User user);

    int deleteUser(User user);

    User findByUserId(Integer id);

    User findByLoginName(User user);
}
