package cn.edu.hncst.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.edu.hncst.entity.User;
import cn.edu.hncst.service.UserService;
import cn.edu.hncst.service.impl.UserServiceImpl;
@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		//接受参数
		//req.getParameter("")(接受参数一个一个的接受)
		//批量接收参数
		/***
		 * 
		 * Map集合 以键值对的形式存在，建要求不重复，偏历map集合两种方式：
		 * 通过key获取keySet()
		 */
		Map<String, String[]> map = req.getParameterMap();
		//把接受的参数封装到User对象中
		//实例化对象
		User user =new User(); 
		/****
		 * user.setName("");(单个封装)
		 */
		try {
			//吧接受的数据封装到user对象中
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//使用多态机制进行实现
		UserService userService =new UserServiceImpl();
		//调用userService中的方法
		userService.add(user);
		//跳转页面(重定向)
		/**
		 * req.getContextPath()相当于
		 * http://localhost:8080/项目名称
		 */
		resp.sendRedirect(req.getContextPath()+"/userListServlet");
	}
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) {
		//调用本类中的dopost方法
		this.doGet(req, resp);
	}
}
