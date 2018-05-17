package _1_ReadUsers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import org.junit.Test;
/**
 * 编写的第一个jdbc程序，程序包含了jdbc访问数据库的完整步骤：
 * 		1.注册MySQL的数据库驱动器类
 * 		2.通过DriverManager获取一个Connection对象
 * 		3.使用Connection对象创建一个Statement对象（statement：声明，代表
 * 				是一系列方法的声明，是一些方法的特征的集合）
 * 		4.Statement对象能通过ExecuteQuery()方法执行SQL语句，并返回结果集
 * 				ResultSet对象。
 * 		5.通过遍历ResultSet对象便可得到最终的查询结果
 * @author supercckai
 *
 */
public class ReadUsers {
	
	/**
	 * 改进：
	 * 1.注册驱动：
	 * 		使用DriverManager.registerDriver(xxx)会使数据库驱动被注册两次，因为
	 * 			Driver类的源码中已经在静态代码中完成了数据库驱动的注册。所以，为
	 * 			了避免数据库驱动被重复注册，只需要在程序中加载驱动类即可。
	 * 2.释放资源：
	 * 		因为数据库资源非常宝贵，数据库允许的并发访问连接数量有限，因此，当数据
	 * 			库资源使用完毕后，为了保证资源的释放，应该将最终必须要执行的操作放
	 * 			在finally代码块中。
	 */
	@Test
	public void testSQL() {
		try {
			//1.注册数据库的驱动
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");
			//2.通过DriverManager获取数据库连接
			//String url = "jdbc:mysql://localhost:3306/chapter01";
			String url = "jdbc:mysql://localhost:3306/chapter01"
					+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String username = "root";
			String passwd = "spcckai";
			Connection conn = DriverManager.getConnection(url, username, passwd);
			//3.通过Connection对象获取Statement对象
			Statement stmt = conn.createStatement();
			//4.使用Statement执行SQL语句
			String sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);
			//5.操作ResultSet结果集
			System.out.println("id|name|password|email|birthday");
			while(rs.next()) {
				int id = rs.getInt("id"); //通过列名获取指定字段的值
				String name = rs.getString("name");
				String psw = rs.getString("password");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				System.out.println(id + "|" + name + "|" + psw + "|" + email
						+ "|" + birthday);
			}
			//6.回收数据库资源
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}
