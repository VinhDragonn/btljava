package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLbtl {
	public static Connection getConnection() {
		Connection con = null;
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
 String connectionUrl = "jdbc:sqlserver://LAPTOP-J7S2M066:1433;databaseName=do_an_javaXYZ;user=sa;password=123456789;encrypt=false;";

  con  = DriverManager.getConnection(connectionUrl);
  
   
        } catch( Exception e) {
        	e.printStackTrace();
        }
		return con;
	}
}
