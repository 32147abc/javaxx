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
		// �����ַ�������
		req.setCharacterEncoding("UTF-8");
		// ��ȡ��ǰҳ
		String currentPage = req.getParameter("currentPage");
		// ��ȡÿҳ��ʾ������
		String rows = req.getParameter("rows");
		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "1";
		}
		// �ж�ÿҳ��ʾ������
		if (rows == null || "".equals(rows)) {
			rows = "2";
		}
		// ��ȡ������ѯ����
		Map<String, String[]> condition = req.getParameterMap();
		// ʹ�ö�̬����
		UserService userService = new UserServiceImpl();
		// ���÷�ҳ����
		PageBean<User> pb = userService.findUserByPage(currentPage, rows, condition);
//		System.out.println(pb);
		// ��pageBean����ŵ�request��Χ��
		req.setAttribute("pb", pb);
		// ���û���������������ŵ�request��Χ��
		req.setAttribute("condition", condition);
		// ��תҳ��
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
	}
}
