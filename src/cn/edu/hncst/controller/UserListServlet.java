package cn.edu.hncst.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hncst.entity.User;
import cn.edu.hncst.service.UserService;
import cn.edu.hncst.service.impl.UserServiceImpl;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		UserService userService =new UserServiceImpl();
		List<User> users = userService.findAll();
		req.setAttribute("users", users);
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
