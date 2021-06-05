package cn.edu.hncst.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hncst.entity.PageBean;
import cn.edu.hncst.entity.User;
import cn.edu.hncst.service.UserService;
import cn.edu.hncst.service.impl.UserServiceImpl;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置字符集编码
		req.setCharacterEncoding("UTF-8");
		// 获取当前页
		String currentPage = req.getParameter("currentPage");
		// 获取每页显示的条数
		String rows = req.getParameter("rows");
		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "1";
		}
		// 判断每页显示的条数
		if (rows == null || "".equals(rows)) {
			rows = "2";
		}
		// 获取条件查询参数
		Map<String, String[]> condition = req.getParameterMap();
		// 使用多态机制
		UserService userService = new UserServiceImpl();
		// 调用分页方法
		PageBean<User> pb = userService.findUserByPage(currentPage, rows, condition);
//		System.out.println(pb);
		// 把pageBean对象放到request范围内
		req.setAttribute("pb", pb);
		// 把用户输入的条件参数放到request范围内
		req.setAttribute("condition", condition);
		// 跳转页面
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
	}
}
