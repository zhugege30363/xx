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
		//分页的控制层
		String action = request.getParameter("action");
		if("findByPage".equals(action)){
			findByPage(request,response);
			System.out.println("到这里了");
		}
		
	}

	private void findByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		// 1.获取页面传递的数据
		String pageSize = request.getParameter("pageSize");
		String currentPage = request.getParameter("currentPage");
		System.out.println("每页的尺寸大小"+pageSize);
		System.out.println("当前页数是"+currentPage);
		String reg = "^[1-9][0-9]*$";
		
		Address address = new Address();
		
		//2.服务器端验证，非空和要求数字
		if(pageSize==null||pageSize.equals("") ){			
			address.setPageSize(5L);			
		}else if(!pageSize.matches(reg)){
			throw new ServletException("分页参数PageSize不符合要求");	
		}else{
			address.setPageSize(Long.parseLong(pageSize));		
		}
		
		if(currentPage==null||currentPage.equals("")){
			address.setCurrentPage(1L);
		}else if(!currentPage.matches(reg)){
				throw new ServletException("分页参数currentPage不符合要求");
		}else{
				address.setCurrentPage(Long.parseLong(currentPage));
		}
	
		
		//调用分页的业务层或dao
		try {
			logger.info("开始查询分页的通讯录");
			//获得总条数
			address.setTotalSize(dao.count(address));
			//获得分页的list
			List<Address> list = dao.findBypage(address);
			//4.跳转时携带的数据
			//System.out.println(list.toString());
			request.setAttribute("pageBean", address);
			request.setAttribute("pagelist", list);
			
			logger.info("查询分页的通讯录成功");
		} catch (Exception e) {
			logger.error("查询分页的通讯录失败",e);
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
