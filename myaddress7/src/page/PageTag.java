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
	

	//�����ǩ���Ǹ÷���
	@Override
	public int doEndTag() throws JspException {
		
		// �Զ����ǩ���������
		
		//��ȡpageBean����
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		Pager pageBean =(Pager) request.getAttribute(pageName);
		if(pageBean==null){
			pageBean = (Pager) request.getSession().getAttribute(pageName);
		}
		if(pageBean == null){
			throw new JspException("�Ҳ�����PageBean,����pageBean��Name�Ƿ���ȷ");
		}
		
		
		//out���
		JspWriter out = pageContext.getOut();		
		
		
		StringBuffer sb = new StringBuffer();
		//ƴ��script��function
		sb.append("<script type='text/javascript'>")
			.append("function goToPage(){")
			.append("document.getElementById('action').value='findByPage';")
			.append(formName)
			.append(".submit();}");
		
		sb.append("function toPage(num){")
		.append("document.getElementById('curPage').value=num;")
		.append("goToPage();}");
		
		sb.append("</script>");
		
		
		//�ܹ�${pageBean.totalSize}��|�ܹ�${pageBean.totalPage}ҳ
		sb.append("�ܹ�").append(pageBean.getTotalSize()).append("��|�ܹ�").append(pageBean.getTotalPage()).append("ҳ");
		
		Long currentPage = pageBean.getCurrentPage(); 
		if(currentPage==1){
				sb.append("��ҳ|��һҳ|").append("	<a href='javascript:toPage(").append(currentPage+1).append(")'>��һҳ</a>|")
					.append("<a href='javascript:toPage(").append(pageBean.getTotalPage()).append(")'>ĩҳ</a>");
		}else if(currentPage==pageBean.getTotalPage()){
			sb.append("<a href='javascript:toPage(1)'>��ҳ</a>|")
			.append("<a href='javascript:toPage(").append(currentPage-1).append(")'>��һҳ</a>|")
			.append("��һҳ|ĩҳ");
		}else{
			sb.append("<a href='javascript:toPage(1)'>��ҳ</a>|")
			.append("<a href='javascript:toPage(").append(currentPage-1).append(")'>��һҳ</a>|")
			.append("	<a href='javascript:toPage(").append(currentPage+1).append(")'>��һҳ</a>|")
			.append("<a href='javascript:toPage(").append(pageBean.getTotalPage()).append(")'>ĩҳ</a>");
		}
		
		sb.append("ÿҳ<input type='text' name='pageSize' value='").append(pageBean.getPageSize()).append("'/>��")
		.append("��<input type='text' id='curPage' name='currentPage' value='").append(currentPage).append("'/>ҳ")
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
