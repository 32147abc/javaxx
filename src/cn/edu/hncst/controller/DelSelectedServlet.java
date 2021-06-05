package cn.edu.hncst.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hncst.service.UserService;
import cn.edu.hncst.service.impl.UserServiceImpl;
@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
		//���ܲ���
		String[] ids = req.getParameterValues("uid");
		//ʹ�ö�̬����
		UserService userService = new UserServiceImpl();
		//��������ɾ������
		userService.delSelectedUser(ids);
		//��תҳ��(�ض���)
		resp.sendRedirect(req.getContextPath()+"/userListServlet");
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
		this.doPost(req, resp);
	}
}
