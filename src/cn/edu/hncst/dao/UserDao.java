package cn.edu.hncst.dao;

import java.util.List;
import java.util.Map;

import cn.edu.hncst.entity.User;

/**
 * user�û��Ľӿ�
 * @author DELL
 *
 */
public interface UserDao {
/**
 * @return List����(User)
 * @param  �޲���
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
	 * ��ѯ�ܼ�¼��
	 * return int ����
	 * @param Map<String,String[]>
	 */
	public int findTotalCount(Map<String, String[]>condition);
	
	/***
	 * ͨ����ҳ���в�ѯ
	 * @return List<User>
	 * @param Map<String,String[]>int����(��ʼ����) start int���� rows(��ʾÿҳ�ĸ���)
	 */
	public List<User> findByPage(int start,int rows,Map<String, String[]> condition);

	User findNamePass(String username, String password);
}
