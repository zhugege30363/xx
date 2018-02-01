package page;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {

	 private String pageName;
	 private String formName;
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public PageTag() {
		super();
	}
	

	//空体标签覆盖该方法
	@Override
	public int doEndTag() throws JspException {
		
		// 自定义标签的输出内容
		
		//获取pageBean对象
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		Pager pageBean =(Pager) request.getAttribute(pageName);
		if(pageBean==null){
			pageBean = (Pager) request.getSession().getAttribute(pageName);
		}
		if(pageBean == null){
			throw new JspException("找不到该PageBean,请检查pageBean的Name是否正确");
		}
		
		
		//out输出
		JspWriter out = pageContext.getOut();		
		
		
		StringBuffer sb = new StringBuffer();
		//拼接script的function
		sb.append("<script type='text/javascript'>")
			.append("function goToPage(){")
			.append("document.getElementById('action').value='findByPage';")
			.append(formName)
			.append(".submit();}");
		
		sb.append("function toPage(num){")
		.append("document.getElementById('curPage').value=num;")
		.append("goToPage();}");
		
		sb.append("</script>");
		
		
		//总共${pageBean.totalSize}条|总共${pageBean.totalPage}页
		sb.append("总共").append(pageBean.getTotalSize()).append("条|总共").append(pageBean.getTotalPage()).append("页");
		
		Long currentPage = pageBean.getCurrentPage(); 
		if(currentPage==1){
				sb.append("首页|上一页|").append("	<a href='javascript:toPage(").append(currentPage+1).append(")'>下一页</a>|")
					.append("<a href='javascript:toPage(").append(pageBean.getTotalPage()).append(")'>末页</a>");
		}else if(currentPage==pageBean.getTotalPage()){
			sb.append("<a href='javascript:toPage(1)'>首页</a>|")
			.append("<a href='javascript:toPage(").append(currentPage-1).append(")'>上一页</a>|")
			.append("下一页|末页");
		}else{
			sb.append("<a href='javascript:toPage(1)'>首页</a>|")
			.append("<a href='javascript:toPage(").append(currentPage-1).append(")'>上一页</a>|")
			.append("	<a href='javascript:toPage(").append(currentPage+1).append(")'>下一页</a>|")
			.append("<a href='javascript:toPage(").append(pageBean.getTotalPage()).append(")'>末页</a>");
		}
		
		sb.append("每页<input type='text' name='pageSize' value='").append(pageBean.getPageSize()).append("'/>条")
		.append("第<input type='text' id='curPage' name='currentPage' value='").append(currentPage).append("'/>页")
		.append("<input type='button' value='go' onclick='goToPage()'/>");
		
		try {
			out.write(sb.toString());
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
	
	
	
	
	
	
}
