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
		//���ܲ���
		//req.getParameter("")(���ܲ���һ��һ���Ľ���)
		//�������ղ���
		/***
		 * 
		 * Map���� �Լ�ֵ�Ե���ʽ���ڣ���Ҫ���ظ���ƫ��map�������ַ�ʽ��
		 * ͨ��key��ȡkeySet()
		 */
		Map<String, String[]> map = req.getParameterMap();
		//�ѽ��ܵĲ�����װ��User������
		//ʵ��������
		User user =new User(); 
		/****
		 * user.setName("");(������װ)
		 */
		try {
			//�ɽ��ܵ����ݷ�װ��user������
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ʹ�ö�̬���ƽ���ʵ��
		UserService userService =new UserServiceImpl();
		//����userService�еķ���
		userService.add(user);
		//��תҳ��(�ض���)
		/**
		 * req.getContextPath()�൱��
		 * http://localhost:8080/��Ŀ����
		 */
		resp.sendRedirect(req.getContextPath()+"/userListServlet");
	}
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) {
		//���ñ����е�dopost����
		this.doGet(req, resp);
	}
}
