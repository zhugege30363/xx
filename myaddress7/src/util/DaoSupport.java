package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Properties;


public class DaoSupport {
	protected  Connection conn;
	

	static{
	//1.采用反射加载数据库的驱动,只加载一次
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public Connection openConn() throws SQLException{
		conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","myaddress","myaddress");
		return conn;
	}
	public PreparedStatement getPrepareSta(String sql) throws SQLException{
		return openConn().prepareStatement(sql);
		}
	public void free(PreparedStatement pst,ResultSet rs) throws SQLException{
		try {
			if(rs!=null)rs.close();
			if(pst!=null)pst.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs=null;
			pst=null;
			conn=null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
