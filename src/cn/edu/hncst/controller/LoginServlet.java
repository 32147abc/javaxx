package cn.edu.hncst.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;//
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;//
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;


import cn.edu.hncst.entity.User;
import cn.edu.hncst.service.UserService;
import cn.edu.hncst.service.impl.UserServiceImpl;
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置字符编码
		req.setCharacterEncoding("utf-8");
		//接收用户输入的验证码(参数中的字符串要与文本框的name属性保持一致)
		String verifycode =req.getParameter("verifycode");
		//通过req获取session对象
		HttpSession session =req.getSession();
		//从session中取出服务器中验证码,通过键获取，返回字符串（但是返回的是object）用字符串接收，强制转换
		String checkcode_server =(String)session.getAttribute("CHECKCODE_SERVER");
		//从session中取消这个键（验证码取消
		session.removeAttribute("CHECKCODE_SERVER");
		//用户输入的验证码和服务器中的验证码进行对比,不区分大小写
		if(!checkcode_server.equalsIgnoreCase(verifycode) ){
			//提示用户验证码错误
			req.setAttribute("login_msg", "验证码错误！！！");
			//跳转页面（登录）（转发
			req.getRequestDispatcher("/login.jsp").forward(req,resp);
			return;//不提交
		}
		//接收用户输入的用户名。密码
		//整体接收参数 req对象
		Map<String, String[]> map =req.getParameterMap();
		//把接受的参数封装到user对象中
		//先实例化对象
		User user =new User();
		//导入实体类包
		try {
			BeanUtils.populate(user, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//使用多态机制
		UserService userService = new UserServiceImpl();
		//进行登陆操作
		User loginUser =userService.login(user);
		//根据参数查询是否能查到记录
		if(loginUser != null){
/*			//需要把查到的user对象保存在request范围内
			req.setAttribute("user", loginUser);
			//跳转页面（转发
			req.getRequestDispatcher("/index.jsp").forward(req, resp);;
	*/
			//把查到的user对象保存在request
			session.setAttribute("user", loginUser);
			resp.sendRedirect(req.getContextPath()+"/userListServlet");
		}else{
			//把提示信息存放在request范围内
			req.setAttribute("login_msg", "用户名或密码错误");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
