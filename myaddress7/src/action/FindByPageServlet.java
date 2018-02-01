package action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.IAddressDao;
import dao.impl.IAddressDaoImpl;

import vo.Address;

public class FindByPageServlet extends HttpServlet {
	IAddressDao dao = new IAddressDaoImpl();
	Logger logger = Logger.getLogger(FindByPageServlet.class);
	/**
	 * Constructor of the object.
	 */
	public FindByPageServlet() {
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
		//��ҳ�Ŀ��Ʋ�
		String action = request.getParameter("action");
		if("findByPage".equals(action)){
			findByPage(request,response);
			System.out.println("��������");
		}
		
	}

	private void findByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		// 1.��ȡҳ�洫�ݵ�����
		String pageSize = request.getParameter("pageSize");
		String currentPage = request.getParameter("currentPage");
		System.out.println("ÿҳ�ĳߴ��С"+pageSize);
		System.out.println("��ǰҳ����"+currentPage);
		String reg = "^[1-9][0-9]*$";
		
		Address address = new Address();
		
		//2.����������֤���ǿպ�Ҫ������
		if(pageSize==null||pageSize.equals("") ){			
			address.setPageSize(5L);			
		}else if(!pageSize.matches(reg)){
			throw new ServletException("��ҳ����PageSize������Ҫ��");	
		}else{
			address.setPageSize(Long.parseLong(pageSize));		
		}
		
		if(currentPage==null||currentPage.equals("")){
			address.setCurrentPage(1L);
		}else if(!currentPage.matches(reg)){
				throw new ServletException("��ҳ����currentPage������Ҫ��");
		}else{
				address.setCurrentPage(Long.parseLong(currentPage));
		}
	
		
		//���÷�ҳ��ҵ����dao
		try {
			logger.info("��ʼ��ѯ��ҳ��ͨѶ¼");
			//���������
			address.setTotalSize(dao.count(address));
			//��÷�ҳ��list
			List<Address> list = dao.findBypage(address);
			//4.��תʱЯ��������
			//System.out.println(list.toString());
			request.setAttribute("pageBean", address);
			request.setAttribute("pagelist", list);
			
			logger.info("��ѯ��ҳ��ͨѶ¼�ɹ�");
		} catch (Exception e) {
			logger.error("��ѯ��ҳ��ͨѶ¼ʧ��",e);
			//throw new ServletException(e);
		}
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
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
			doGet(request, response);
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
