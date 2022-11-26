package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	
	protected ReadFile rf = new ReadFile();
	protected String url = rf.getString("url_db");
	protected String user = rf.getString("user");
	protected String pass = rf.getString("pass");
	
	public Statement perform(String url, String user, String pass) 
			throws ClassNotFoundException, SQLException{
		
		Statement st;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection ct = DriverManager.getConnection(url,user,pass);
		st = ct.createStatement();
		return st;
	
	}
	
	public void getTableContent(String tbl) throws ClassNotFoundException, SQLException {
		try {
		Statement st = perform(url, user, pass);
		ResultSet rs =  st.executeQuery("select * from "+tbl+" order by 1 desc;");
		
		System.out.println("Information of table : "+tbl);
		System.out.println("Name | Price | Category | Date Deal");
		
		while(rs.next()) {
			System.out.println(rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));			
		}
		}catch(Exception e) {
			System.out.println("Exception on getTableContent(x) > "+e);
		}
	}

	public void setTableData(String tbl, String a, double b, String c) throws ClassNotFoundException, SQLException {
		double _id = Math.round(Math.random()*1000)+1;
		String sql="";
		try {
		Statement st = perform(url, user, pass);
		sql = "insert into "+tbl+" values ("+_id+",'"+a+"',"+b+",'"+c+"',null);";
		//System.out.println("["+sql+"]");
		st.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("Exception on setTableData(x,y) > "+e);
		}
	}
	
	
}
