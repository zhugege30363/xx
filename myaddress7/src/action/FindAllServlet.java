package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Address;
import dao.IAddressDao;
import dao.impl.IAddressDaoImpl;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionAttributeListener;

import org.apache.log4j.Logger;

public class FindAllServlet extends HttpServlet {
	IAddressDao dao = new IAddressDaoImpl();
	Logger logger=Logger.getLogger(FindAllServlet.class);
	/**
	 * Constructor of the object.
	 */
	public FindAllServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��post���������봦��
		//request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("findAll".equals(action)){
			//���������ҵ���ʶaction��ȥ���ö�Ӧ�Ŀ��Ʋ㷽��
			findAll(request, response);
		}else if("addAddress".equals(action)){
			add(request, response);
		}
	}

	private void findAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ����

		try {
			logger.info("��ʼ��ѯ���е�ͨѶ¼");
			List<Address> list = dao.findAll();
			//��һ��������������Ч
			request.setAttribute("addressList", list);
			logger.info("��ѯ����ͨѶ¼�ɹ�");
		} catch (Exception e) {
			logger.error("��ѯ����ͨѶ¼ʧ��",e);
			//throw new ServletException(e);
		}
		
		
		//��ת��ҳ��
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
	
	private void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		//�����Ŀ��Ʋ㴦��
		String username = request.getParameter("username");
		//��get����������Ĵ���
		String username2 = new String(username.getBytes("ISO8859-1"),"UTF-8");
		System.out.println(username2);
		String email = request.getParameter("email");
		System.out.println(email);
		//��������װΪ����
		Address address = new Address(username2,email);
		//����ҵ����dao
		try {
			dao.add(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		//��ת��servlet��������jsp
		//findAll(request,response); //����д��
		request.getRequestDispatcher("findAllServlet?action=findAll").forward(request, response);
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
