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
		//�����ַ�����
		req.setCharacterEncoding("utf-8");
		//�����û��������֤��(�����е��ַ���Ҫ���ı����name���Ա���һ��)
		String verifycode =req.getParameter("verifycode");
		//ͨ��req��ȡsession����
		HttpSession session =req.getSession();
		//��session��ȡ������������֤��,ͨ������ȡ�������ַ��������Ƿ��ص���object�����ַ������գ�ǿ��ת��
		String checkcode_server =(String)session.getAttribute("CHECKCODE_SERVER");
		//��session��ȡ�����������֤��ȡ��
		session.removeAttribute("CHECKCODE_SERVER");
		//�û��������֤��ͷ������е���֤����жԱ�,�����ִ�Сд
		if(!checkcode_server.equalsIgnoreCase(verifycode) ){
			//��ʾ�û���֤�����
			req.setAttribute("login_msg", "��֤����󣡣���");
			//��תҳ�棨��¼����ת��
			req.getRequestDispatcher("/login.jsp").forward(req,resp);
			return;//���ύ
		}
		//�����û�������û���������
		//������ղ��� req����
		Map<String, String[]> map =req.getParameterMap();
		//�ѽ��ܵĲ�����װ��user������
		//��ʵ��������
		User user =new User();
		//����ʵ�����
		try {
			BeanUtils.populate(user, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//ʹ�ö�̬����
		UserService userService = new UserServiceImpl();
		//���е�½����
		User loginUser =userService.login(user);
		//���ݲ�����ѯ�Ƿ��ܲ鵽��¼
		if(loginUser != null){
/*			//��Ҫ�Ѳ鵽��user���󱣴���request��Χ��
			req.setAttribute("user", loginUser);
			//��תҳ�棨ת��
			req.getRequestDispatcher("/index.jsp").forward(req, resp);;
	*/
			//�Ѳ鵽��user���󱣴���request
			session.setAttribute("user", loginUser);
			resp.sendRedirect(req.getContextPath()+"/userListServlet");
		}else{
			//����ʾ��Ϣ�����request��Χ��
			req.setAttribute("login_msg", "�û������������");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
