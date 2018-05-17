package _4_useResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * 在_1_ReadUsers中的ResultSet操作中，ResultSet主要是用于存储结果集，并且只能通过
 * 		next()方法由前向后逐个获取结果集中的数据。但是，如果想获取结果集中任意位置
 * 		的数据，则需要在创建Statement对象时，设置两个ResultSet定义的常量。
 * @author supercckai
 *
 */
public class TestResultSet {
	
	@Test
	public void test() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/chapter01"
					+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String username = "root";
			String password = "spcckai";
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("第2条数据的name值是：");
			rs.absolute(2); //将指针定位到结果集中第2行数据
			System.out.println(rs.getString("name"));
			System.out.println("第1条数据的name值是：");
			rs.beforeFirst(); //将指针定位到结果集中第一行数据之前
			rs.next(); //将指针向后滚动
			System.out.println(rs.getString("name"));
			System.out.println("第4条数据的name值是：");
			rs.afterLast(); //将指针定位到结果集中最后一行数据之后
			rs.previous(); //将指针向前滚动
			System.out.println(rs.getString("name"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally { //释放资源
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}//if
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}//if
		}
	}
	
}
