import java.sql.SQLException;

import util.DaoSupport;


public class test {
	public static void main(String[] args) throws SQLException {
		DaoSupport dt=new DaoSupport();
		System.out.println(dt.openConn());
	}

}
