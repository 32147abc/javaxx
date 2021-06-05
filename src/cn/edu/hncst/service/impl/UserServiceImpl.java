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
			 * ����������
			 * for()
			 * do.....while()
			 * ��ǿfor()
			 * while()
			 */
			for(String id : ids){
				userDao.delete(Integer.parseInt(id));
			}
		}
	}
	
	public User login(User user) {
		//ֱ�ӵ�¼��ʹ�ú��̬
		
		return userDao.login(user.getUsername(),user.getPassword());
	}
	
	//��ҳ��ѯ
	public PageBean<User> findUserByPage(String _currentPage,String _rows,Map<String, String[]> condition){
		//��ȡ��ǰҳ
		int currentPage =Integer.parseInt(_currentPage);
		//��ȡÿҳ��ʾ������
		int rows = Integer.parseInt(_rows);
		//�жϵ�ǰҳ����С��0
		if (currentPage <=0) {
			currentPage =1;
		}
		//ʵ��������(PageBean)
		PageBean<User> pb = new PageBean<User>();
		//��pb��ֵ
		pb.setCurrentPage(currentPage);
		pb.setRows(rows);
		//��ȡ�ܼ�¼��
		int totalCount=userDao.findTotalCount(condition);
		pb.setTotalCount(totalCount);
		//���㿪ʼ������ֵ((��ǰҳ-1)*pageSize)
		int start =(currentPage -1)*rows;
		//��ȡÿҳ����
		List<User> list = userDao.findByPage(start, rows, condition);
		pb.setList(list);
		//������ҳ��(�ܼ�¼�� % rows)
		/***
		 * ��Ԫ�����
		 */
		int totalPage = (totalCount % rows)==0 ? totalCount/rows : (totalCount/rows) + 1;
		pb.setTotalPage(totalPage);
		return pb;
	}
	
	public User loginUser(String username,String password){
		return userDao.login(username,password);
	}
}
