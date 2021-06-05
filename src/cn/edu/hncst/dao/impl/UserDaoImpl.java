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
	// 获取数据库连接信息
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	// 查询所有
	public List<User> findAll() {
		// 定义SQL语句
		String sql = "Select * from user";
		// 执行SQL语句
		List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
		return users;
	}

	// 添加
	public void add(User user) {
		String sql = "INSERT INTO user VALUES (null,?,?,?,?,?,?,null,null)";
		template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(),
				user.getEmail());
	}

	// 根据id删除
	public void delete(int id) {
		String sql = "DELETE FROM user WHERE id = ?";
		template.update(sql, id);
	}

	// 根据id查询
	public User findById(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		// 执行SQL语句并得到user对象
		return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);

	}

	// 更新
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

	// 查询总记录数
	public int findTotalCount(Map<String, String[]> condition) {
		// 初始化Sql语句
		String sql = "SELECT COUNT(*) FROM user WHERE 1 = 1 ";
		// 实例化一个StringBuilder对象
		StringBuilder sb = new StringBuilder(sql);
		// 实例化List集合存放map
		List<Object> params = new ArrayList<Object>();
		// 获取用户传递的键
		Set<String> keySet = condition.keySet();
		// 遍历Map的键
		for (String key : keySet) {
			// 排除分页条件参数
			if ("currentPage".equals(key) || "rows".equals(key)) {
				continue;
			}
			// 获取value的值
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

	// 分页查询
	public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
		String sql = "SELECT * FROM user WHERE 1 = 1 ";
		// 实例化StringBuilder用于拼接sql语句
		StringBuilder sb = new StringBuilder(sql);
		// 实例化List集合存放Map的值
		List<Object> params = new ArrayList<Object>();
		// 获取map键
		Set<String> keySet = condition.keySet();
		// 遍历map的键
		for (String key : keySet) {
			// 排除分页条件参数
			if ("currentPage".equals(key) || "rows".equals(key)) {
				continue;
			}
			// 获取value值
			String value = condition.get(key)[0];
			// 判断value是否有值
			if (value != null && !"".equals(value)) {
				// 如果有值
				sb.append(" and " + key + " like ? ");
				params.add("%" + value + "%");
			}
		}
		// 添加分页查询
		sb.append(" limit ? , ? ");
		// 添加分页查询参数值
		params.add(start);
		params.add(rows);
		// 给sql赋值
		sql = sb.toString();
		System.out.println(sql);
		System.out.println(params);
		return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
	}

	public User login(String username, String password) {
		return null;
	}

}