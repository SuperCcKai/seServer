package _utils.loginSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 操作数据库
 * @author supercckai
 *
 */
public class JDBCUtils {
	
	//加载驱动，并建立数据库连接，返回Connection对象
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/loginTest"
				+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		String username = "root";
		String password = "spcckai";
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	//关闭数据库连接，释放资源
	public static void release(Statement stmt, Connection conn) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}//if
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	//重载release()
	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		release(stmt, conn);
	}
	
	
}
