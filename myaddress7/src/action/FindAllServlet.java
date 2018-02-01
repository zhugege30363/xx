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
		//对post请求做乱码处理
		//request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("findAll".equals(action)){
			//根据请求的业务标识action，去调用对应的控制层方法
			findAll(request, response);
		}else if("addAddress".equals(action)){
			add(request, response);
		}
	}

	private void findAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询所有

		try {
			logger.info("开始查询所有的通讯录");
			List<Address> list = dao.findAll();
			//在一个请求内数据有效
			request.setAttribute("addressList", list);
			logger.info("查询所有通讯录成功");
		} catch (Exception e) {
			logger.error("查询所有通讯录失败",e);
			//throw new ServletException(e);
		}
		
		
		//跳转到页面
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
	
	private void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		//新增的控制层处理
		String username = request.getParameter("username");
		//对get请求做乱码的处理
		String username2 = new String(username.getBytes("ISO8859-1"),"UTF-8");
		System.out.println(username2);
		String email = request.getParameter("email");
		System.out.println(email);
		//将参数封装为对象
		Address address = new Address(username2,email);
		//调用业务层或dao
		try {
			dao.add(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		//跳转到servlet，而不是jsp
		//findAll(request,response); //错误写法
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
