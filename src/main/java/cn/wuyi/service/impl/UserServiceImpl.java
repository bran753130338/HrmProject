package cn.wuyi.service.impl;

import cn.wuyi.dao.UserDao;
import cn.wuyi.dao.impl.UserDaoImpl;
import cn.wuyi.domain.PageBean;
import cn.wuyi.domain.QueryInfo;
import cn.wuyi.domain.QueryResult;
import cn.wuyi.domain.User;
import cn.wuyi.service.UserService;


/**
 * UserService实现类
 * 
 * @author newuser
 *
 */
public class UserServiceImpl implements UserService {
	private UserDao userdao = new UserDaoImpl();

	@Override
	public int loginByLoginname(User user) {
		User user_rs = userdao.findByLoginname(user);
		// 如果返回的结果！=null说明有这个用户，将这个用户返回的密码与用户输入的密码做比较
		if (user_rs != null) {

			if (user.getPassword().equals(user_rs.getPassword())) {

				// 登录成功
				return 2;
			} else {
				// 密码错误
				return 1;
			}

		}
		// 没有这个用户
		return 0;
	}

	// 2.添加用户的方法
	@Override
	public int addUser(User user) {
		// 返回查询的结果
		return userdao.addUser(user);
	}

	public PageBean pageQuery(QueryInfo queryInfo) {

		// 调用dao获取到页面数据
		QueryResult qr = userdao.pageQuery(queryInfo.getStartindex(), queryInfo.getPagesize());

		// 根据dao查询结果，生成页面显示需要pagebean
		PageBean bean = new PageBean();
		bean.setCurrentpage(queryInfo.getCurrentpage());
		bean.setList(qr.getList());
		bean.setPagesize(queryInfo.getPagesize());
		bean.setTotalrecord(qr.getTotalrecord());

		return bean;
	}

    @Override
    public int UpdateUser(User user) {
        return userdao.updateUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return userdao.deleteUser(user);
    }

    @Override
    public User findByUserId(Integer id) {

        return userdao.findByUserId(id);
    }

    @Override
    public User findByLoginName(User user) {
        return userdao.findByLoginname(user);
    }

}
