package cn.edu.hncst.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.edu.hncst.dao.UserDao;
import cn.edu.hncst.entity.User;
import cn.edu.hncst.util.JDBCUtils;

public class UserDaoImpl implements UserDao {
	// ��ȡ���ݿ�������Ϣ
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	// ��ѯ����
	public List<User> findAll() {
		// ����SQL���
		String sql = "Select * from user";
		// ִ��SQL���
		List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
		return users;
	}

	// ���
	public void add(User user) {
		String sql = "INSERT INTO user VALUES (null,?,?,?,?,?,?,null,null)";
		template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(),
				user.getEmail());
	}

	// ����idɾ��
	public void delete(int id) {
		String sql = "DELETE FROM user WHERE id = ?";
		template.update(sql, id);
	}

	// ����id��ѯ
	public User findById(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		// ִ��SQL��䲢�õ�user����
		return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);

	}

	// ����
	public void update(User user) {
		String sql = "UPDATE user SET name = ?, gender = ?, age = ?, address = ?, qq = ?, email = ? WHERE id = ?";
		template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(),
				user.getEmail(), user.getId());
	}

	public User findNamePass(String username, String password) {
		try {
			String sql = "SELECT * FROM user WHERE username = ? and password = ?";
			User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
			return user;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ��ѯ�ܼ�¼��
	public int findTotalCount(Map<String, String[]> condition) {
		// ��ʼ��Sql���
		String sql = "SELECT COUNT(*) FROM user WHERE 1 = 1 ";
		// ʵ����һ��StringBuilder����
		StringBuilder sb = new StringBuilder(sql);
		// ʵ����List���ϴ��map
		List<Object> params = new ArrayList<Object>();
		// ��ȡ�û����ݵļ�
		Set<String> keySet = condition.keySet();
		// ����Map�ļ�
		for (String key : keySet) {
			// �ų���ҳ��������
			if ("currentPage".equals(key) || "rows".equals(key)) {
				continue;
			}
			// ��ȡvalue��ֵ
			String value = condition.get(key)[0];
			if (value != null && !"".equals(value)) {
				sb.append(" and " + key + " like ? ");
				params.add("%" + value + "%");
			}
		}
		System.out.println(sb.toString());
		System.out.println(params);
		return template.queryForObject(sb.toString(), Integer.class, params.toArray());
	}

	// ��ҳ��ѯ
	public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
		String sql = "SELECT * FROM user WHERE 1 = 1 ";
		// ʵ����StringBuilder����ƴ��sql���
		StringBuilder sb = new StringBuilder(sql);
		// ʵ����List���ϴ��Map��ֵ
		List<Object> params = new ArrayList<Object>();
		// ��ȡmap��
		Set<String> keySet = condition.keySet();
		// ����map�ļ�
		for (String key : keySet) {
			// �ų���ҳ��������
			if ("currentPage".equals(key) || "rows".equals(key)) {
				continue;
			}
			// ��ȡvalueֵ
			String value = condition.get(key)[0];
			// �ж�value�Ƿ���ֵ
			if (value != null && !"".equals(value)) {
				// �����ֵ
				sb.append(" and " + key + " like ? ");
				params.add("%" + value + "%");
			}
		}
		// ��ӷ�ҳ��ѯ
		sb.append(" limit ? , ? ");
		// ��ӷ�ҳ��ѯ����ֵ
		params.add(start);
		params.add(rows);
		// ��sql��ֵ
		sql = sb.toString();
		System.out.println(sql);
		System.out.println(params);
		return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
	}

	public User login(String username, String password) {
		return null;
	}

}