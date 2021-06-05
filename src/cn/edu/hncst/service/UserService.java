package cn.edu.hncst.service;

import java.util.List;
import java.util.Map;

import cn.edu.hncst.entity.PageBean;
import cn.edu.hncst.entity.User;

public interface UserService {

	public List<User> findAll();

	public void add(User user);

	public void deleteUser(String id);
	
	public User findUserById(String id);
	
	public void updateUser(User user);
	
	public void delSelectedUser(String[] ids);
	
	public User login(User user);
	
	public PageBean<User> findUserByPage(String _currentPage,String _rows,Map<String, String[]> condition);

	public User loginUser(String username,String password);

}
