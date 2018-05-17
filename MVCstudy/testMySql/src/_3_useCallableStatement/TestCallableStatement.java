package _3_useCallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.Test;


/**
 * CallableStatement接口是用于执行SQL存储过程的接口，它继承自PrepareStatement接
 * 		口。
 * 
 * jdbc API提供了一个存储过程SQL转义语法，该语法允许对所有关系型数据库管理系统（
 * 		RDBMS）使用标准方式调用存储过程。具体格式如下：
 * 			{?=call<procedure-name>[(<arg1>, <arg2>, ...)]}
 * 			{call<procedure-name>[(<arg1>, <arg2>, ...)]}
 * 
 * 在上述语法格式中，其中的参数（<arg1>, <arg2>, ...）有三种不同的形式，具体如下：
 * 		(1)IN类型：此类型是用于参数从外部传递给存储过程使用。
 * 		(2)OUT类型：此类型是存储过程执行过程中的返回值。
 * 		(3)IN、OUT混合类型：此类型是参数传入，然后返回。
 * 
 * 如果使用结果参数，则必须将其注册为OUT参数。其他参数可用于输入、输出或同时用于二
 * 		者。参数是根据编号按顺序引用的，第一个参数的编号是1。
 * 
 * IN参数值是使用继承自PrepareStatement的setXxx()方法设置的。在执行存储过程之前，
 * 		必须注册所有OUT参数的类型；它们的值是在执行后通过此类提供的getXxx()方法获
 * 		取的。
 * 
 * CAllableStatement可以返回一个或多个ResultSet对象。多个ResultSet对象是通过继承
 * 		Statement来处理的。
 * 
 * 下面的SQL语句用于在chapter01数据库中创建一个简单的存储过程（此程序前提）:
 * 		```
 * 		mysql>DELIMITER$
 * 		mysql>CREATE PROCEDURE add_pro(a INT, b INT, OUT sum INT)
 * 		mysql>BEGIN
 * 		mysql>SET sum=a+b;
 * 		mysql>END$
 * 		mysql>DELIMITER;
 * 		```
 * 
 * 上面的程序创建了名为add_pro的存储过程，该存储过程包含三个参数：a、b是默认参数，
 * 		即传入参数，而sum使用out修饰，是传出参数。
 * 
 * 调用存储过程使用CollableStatement，可以通过Connection的prepareCall()方法来创
 * 		建CallableStatement对象，创建该对象时需要传入调用存储过程的SQL语句。
 * @author supercckai
 *
 */
public class TestCallableStatement {
	
	@Test
	public void test() {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			//注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//通过DriverManager获取数据库连接
			String url = "jdbc:mysql://localhost:3306/chapter01"
					+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String username = "root";
			String password = "spcckai";
			conn = DriverManager.getConnection(url, username, password);
			//使用Connection来创建一个CallableStatement对象
			cstmt = conn.prepareCall("call add_pro(?,?,?)");
			cstmt.setInt(1, 4);
			cstmt.setInt(2, 5);
			//注册CAllableStatement的第三个参数为int类型
			cstmt.registerOutParameter(3, Types.INTEGER);
			//执行存储过程
			cstmt.execute();
			System.out.println("执行结果是：" + cstmt.getInt(3));
			//finally回收数据库资源
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			if(cstmt != null) {
				try {
					cstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
