package cn.edu.hncst.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hncst.entity.User;
import cn.edu.hncst.service.UserService;
import cn.edu.hncst.service.impl.UserServiceImpl;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收id参数
		String id = req.getParameter("id");
		// 是用多态调用接口中的实现类
		UserService userService = new UserServiceImpl();
		User user = userService.findUserById(id);

		// 把查到的数据放到request范围内
		req.setAttribute("user", user);
		req.getRequestDispatcher("/update.jsp").forward(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
