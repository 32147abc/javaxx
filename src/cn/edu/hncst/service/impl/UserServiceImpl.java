package cn.edu.hncst.service.impl;

import java.util.List;
import java.util.Map;

import cn.edu.hncst.dao.UserDao;
import cn.edu.hncst.dao.impl.UserDaoImpl;
import cn.edu.hncst.entity.PageBean;
import cn.edu.hncst.entity.User;
import cn.edu.hncst.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao =new UserDaoImpl();
	
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	public void add(User user){
		userDao.add(user);
	}
	public void deleteUser(String id){
		userDao.delete(Integer.parseInt(id));
	}
	public User findUserById(String id) {
		return userDao.findById(Integer.parseInt(id));
	}
	public void updateUser(User user){
		userDao.update(user);
	}
	public void delSelectedUser(String[] ids){
		if(ids != null && ids.length > 0){
			/**
			 * 遍历的数组
			 * for()
			 * do.....while()
			 * 加强for()
			 * while()
			 */
			for(String id : ids){
				userDao.delete(Integer.parseInt(id));
			}
		}
	}
	
	public User login(User user) {
		//直接登录，使用后多态
		
		return userDao.login(user.getUsername(),user.getPassword());
	}
	
	//分页查询
	public PageBean<User> findUserByPage(String _currentPage,String _rows,Map<String, String[]> condition){
		//获取当前页
		int currentPage =Integer.parseInt(_currentPage);
		//获取每页显示的条数
		int rows = Integer.parseInt(_rows);
		//判断当前页不能小于0
		if (currentPage <=0) {
			currentPage =1;
		}
		//实例化对象(PageBean)
		PageBean<User> pb = new PageBean<User>();
		//给pb赋值
		pb.setCurrentPage(currentPage);
		pb.setRows(rows);
		//获取总记录数
		int totalCount=userDao.findTotalCount(condition);
		pb.setTotalCount(totalCount);
		//计算开始的索引值((当前页-1)*pageSize)
		int start =(currentPage -1)*rows;
		//获取每页数据
		List<User> list = userDao.findByPage(start, rows, condition);
		pb.setList(list);
		//计算总页码(总记录数 % rows)
		/***
		 * 三元运算符
		 */
		int totalPage = (totalCount % rows)==0 ? totalCount/rows : (totalCount/rows) + 1;
		pb.setTotalPage(totalPage);
		return pb;
	}
	
	public User loginUser(String username,String password){
		return userDao.login(username,password);
	}
}
