package util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountListener implements ServletContextListener,HttpSessionListener{

	int count = 0;
	ServletContext application=null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		application = event.getServletContext();

	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
		count++;
		application.setAttribute("count",count);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		count--;
		application.setAttribute("count",count);
	}


}
