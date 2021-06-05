package cn.edu.hncst.controller;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hncst.service.UserService;
import cn.edu.hncst.service.impl.UserServiceImpl;
@WebServlet("/delUserServlet")
public class DeleteUserServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接受id参数
		String id = req.getParameter("id");
		///使用多态
		UserService userService=new UserServiceImpl();
		//调用userService中的方法
		userService.deleteUser(id);
		//跳转页面(重定向)
		resp.sendRedirect(req.getContextPath()+"/userListServlet");
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
