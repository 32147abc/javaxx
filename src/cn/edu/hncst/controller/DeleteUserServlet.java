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
		//����id����
		String id = req.getParameter("id");
		///ʹ�ö�̬
		UserService userService=new UserServiceImpl();
		//����userService�еķ���
		userService.deleteUser(id);
		//��תҳ��(�ض���)
		resp.sendRedirect(req.getContextPath()+"/userListServlet");
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
