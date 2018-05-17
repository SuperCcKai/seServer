package _2_usePreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import org.junit.Test;
/**
 * 第一个包内类中SQL语句的执行使用过Statement对象实现的，Statement对象每次执行SQL
 * 		语句时，都会对其进行编译；当相同的SQL语句执行多次时，Statement对象就会使数
 * 		据库频繁编译相同的SQL语句，从而降低数据库的访问效率。
 * 
 * 解决上述问题，就用到了PrepareStatement对象，其可以对SQL语句进行预编译。
 * 
 * 也就是说，当相同的SQL语句再次执行时，数据库只需使用缓冲区中的数据，而不需要对SQL
 * 		语句再次编译，从而有效提高数据的访问效率。
 * 
 * 此程序介绍了PrepareStatement对象的基本使用
 * @author supercckai
 *
 */
public class TestPreparedStmt {
	
	@Test
	public void test() {
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/chapter01"
					+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String username = "root";
			String password = "spcckai";
			//创建应用程序与数据库连接的Connection对象
			conn = DriverManager.getConnection(url, username, password);
			//执行的sql语句
			String sql = "INSERT INTO users(name, password, email, birthday)"
					+ "VALUES(?, ?, ?, ?)";
			//创建执行sql语句的PreparedStatement对象
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, "zl");
			preStmt.setString(2, "123456");
			preStmt.setString(3, "zl@sina.com");
			preStmt.setString(4, "1789-12-23");
			preStmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally { //释放资源
			if(preStmt != null) {
				try {
					preStmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
				preStmt = null;
			}//if
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
				conn = null;
			}//if
		}//finally
	}//void test()
	
}
