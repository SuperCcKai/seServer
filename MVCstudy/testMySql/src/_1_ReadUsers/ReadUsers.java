package _1_ReadUsers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import org.junit.Test;
/**
 * ��д�ĵ�һ��jdbc���򣬳��������jdbc�������ݿ���������裺
 * 		1.ע��MySQL�����ݿ���������
 * 		2.ͨ��DriverManager��ȡһ��Connection����
 * 		3.ʹ��Connection���󴴽�һ��Statement����statement������������
 * 				��һϵ�з�������������һЩ�����������ļ��ϣ�
 * 		4.Statement������ͨ��ExecuteQuery()����ִ��SQL��䣬�����ؽ����
 * 				ResultSet����
 * 		5.ͨ������ResultSet�����ɵõ����յĲ�ѯ���
 * @author supercckai
 *
 */
public class ReadUsers {
	
	/**
	 * �Ľ���
	 * 1.ע��������
	 * 		ʹ��DriverManager.registerDriver(xxx)��ʹ���ݿ�������ע�����Σ���Ϊ
	 * 			Driver���Դ�����Ѿ��ھ�̬��������������ݿ�������ע�ᡣ���ԣ�Ϊ
	 * 			�˱������ݿ��������ظ�ע�ᣬֻ��Ҫ�ڳ����м��������༴�ɡ�
	 * 2.�ͷ���Դ��
	 * 		��Ϊ���ݿ���Դ�ǳ��������ݿ�����Ĳ������������������ޣ���ˣ�������
	 * 			����Դʹ����Ϻ�Ϊ�˱�֤��Դ���ͷţ�Ӧ�ý����ձ���Ҫִ�еĲ�����
	 * 			��finally������С�
	 */
	@Test
	public void testSQL() {
		try {
			//1.ע�����ݿ������
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");
			//2.ͨ��DriverManager��ȡ���ݿ�����
			//String url = "jdbc:mysql://localhost:3306/chapter01";
			String url = "jdbc:mysql://localhost:3306/chapter01"
					+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String username = "root";
			String passwd = "spcckai";
			Connection conn = DriverManager.getConnection(url, username, passwd);
			//3.ͨ��Connection�����ȡStatement����
			Statement stmt = conn.createStatement();
			//4.ʹ��Statementִ��SQL���
			String sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);
			//5.����ResultSet�����
			System.out.println("id|name|password|email|birthday");
			while(rs.next()) {
				int id = rs.getInt("id"); //ͨ��������ȡָ���ֶε�ֵ
				String name = rs.getString("name");
				String psw = rs.getString("password");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				System.out.println(id + "|" + name + "|" + psw + "|" + email
						+ "|" + birthday);
			}
			//6.�������ݿ���Դ
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
