package util;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class EncodeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request2, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// �����������Ĵ�����
		//System.out.println("������������");
		HttpServletRequest request =(HttpServletRequest)request2;
	    String method =	request.getMethod();
	    
	    if("POST".equals(method)){
	    	request.setCharacterEncoding("UTF-8");
	    }else{
	    	//get����
	    	//�������л�ȡ���еĲ������뼯��Map�У��Ӽ�����ȡ��ÿһ��������value����ת��
	    	//�õ����������в����ļ�ֵ�Զ���
			Map map = request.getParameterMap();
			//��map��ÿһ��ֵ���б���ת��
			Set keySet = map.keySet();
			Iterator it = keySet.iterator();
			while(it.hasNext()){
				Object value = map.get(it.next());
				//�ж�value�Ƿ�Ϊ�ַ�������
				if(value instanceof String[]){
					String[] values = (String[]) value;
					for(int i=0;i<values.length;i++){
						values[i] = new String(values[i].getBytes("ISO-8859-1"),"utf-8");
					}
				}else if(value instanceof String){
					String strValue = (String) value;
					strValue = new String(strValue.getBytes("ISO-8859-1"),"utf-8");
				}
			}

	    }
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
