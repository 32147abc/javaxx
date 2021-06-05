package cn.edu.hncst.dao;

import java.util.List;
import java.util.Map;

import cn.edu.hncst.entity.User;

/**
 * user用户的接口
 * @author DELL
 *
 */
public interface UserDao {
/**
 * @return List集合(User)
 * @param  无参数
 * 
 * 
 */
	public List<User> findAll();

	public void add(User user);
	
	public void delete(int id);

	public User findById(int id);
	
	public void update(User user);
	
	public User login(String username,String password);
	
	/**
	 * 查询总记录数
	 * return int 类型
	 * @param Map<String,String[]>
	 */
	public int findTotalCount(Map<String, String[]>condition);
	
	/***
	 * 通过分页进行查询
	 * @return List<User>
	 * @param Map<String,String[]>int类型(起始索引) start int类型 rows(显示每页的个数)
	 */
	public List<User> findByPage(int start,int rows,Map<String, String[]> condition);

	User findNamePass(String username, String password);
}
