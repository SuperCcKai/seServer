package _3_useCallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.Test;


/**
 * CallableStatement�ӿ�������ִ��SQL�洢���̵Ľӿڣ����̳���PrepareStatement��
 * 		�ڡ�
 * 
 * jdbc API�ṩ��һ���洢����SQLת���﷨�����﷨��������й�ϵ�����ݿ����ϵͳ��
 * 		RDBMS��ʹ�ñ�׼��ʽ���ô洢���̡������ʽ���£�
 * 			{?=call<procedure-name>[(<arg1>, <arg2>, ...)]}
 * 			{call<procedure-name>[(<arg1>, <arg2>, ...)]}
 * 
 * �������﷨��ʽ�У����еĲ�����<arg1>, <arg2>, ...�������ֲ�ͬ����ʽ���������£�
 * 		(1)IN���ͣ������������ڲ������ⲿ���ݸ��洢����ʹ�á�
 * 		(2)OUT���ͣ��������Ǵ洢����ִ�й����еķ���ֵ��
 * 		(3)IN��OUT������ͣ��������ǲ������룬Ȼ�󷵻ء�
 * 
 * ���ʹ�ý������������뽫��ע��ΪOUT�����������������������롢�����ͬʱ���ڶ�
 * 		�ߡ������Ǹ��ݱ�Ű�˳�����õģ���һ�������ı����1��
 * 
 * IN����ֵ��ʹ�ü̳���PrepareStatement��setXxx()�������õġ���ִ�д洢����֮ǰ��
 * 		����ע������OUT���������ͣ����ǵ�ֵ����ִ�к�ͨ�������ṩ��getXxx()������
 * 		ȡ�ġ�
 * 
 * CAllableStatement���Է���һ������ResultSet���󡣶��ResultSet������ͨ���̳�
 * 		Statement������ġ�
 * 
 * �����SQL���������chapter01���ݿ��д���һ���򵥵Ĵ洢���̣��˳���ǰ�ᣩ:
 * 		```
 * 		mysql>DELIMITER$
 * 		mysql>CREATE PROCEDURE add_pro(a INT, b INT, OUT sum INT)
 * 		mysql>BEGIN
 * 		mysql>SET sum=a+b;
 * 		mysql>END$
 * 		mysql>DELIMITER;
 * 		```
 * 
 * ����ĳ��򴴽�����Ϊadd_pro�Ĵ洢���̣��ô洢���̰�������������a��b��Ĭ�ϲ�����
 * 		�������������sumʹ��out���Σ��Ǵ���������
 * 
 * ���ô洢����ʹ��CollableStatement������ͨ��Connection��prepareCall()��������
 * 		��CallableStatement���󣬴����ö���ʱ��Ҫ������ô洢���̵�SQL��䡣
 * @author supercckai
 *
 */
public class TestCallableStatement {
	
	@Test
	public void test() {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			//ע�����ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			//ͨ��DriverManager��ȡ���ݿ�����
			String url = "jdbc:mysql://localhost:3306/chapter01"
					+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String username = "root";
			String password = "spcckai";
			conn = DriverManager.getConnection(url, username, password);
			//ʹ��Connection������һ��CallableStatement����
			cstmt = conn.prepareCall("call add_pro(?,?,?)");
			cstmt.setInt(1, 4);
			cstmt.setInt(2, 5);
			//ע��CAllableStatement�ĵ���������Ϊint����
			cstmt.registerOutParameter(3, Types.INTEGER);
			//ִ�д洢����
			cstmt.execute();
			System.out.println("ִ�н���ǣ�" + cstmt.getInt(3));
			//finally�������ݿ���Դ
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
