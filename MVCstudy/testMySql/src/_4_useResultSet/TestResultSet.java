package _4_useResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * ��_1_ReadUsers�е�ResultSet�����У�ResultSet��Ҫ�����ڴ洢�����������ֻ��ͨ��
 * 		next()������ǰ��������ȡ������е����ݡ����ǣ�������ȡ�����������λ��
 * 		�����ݣ�����Ҫ�ڴ���Statement����ʱ����������ResultSet����ĳ�����
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
			System.out.println("��2�����ݵ�nameֵ�ǣ�");
			rs.absolute(2); //��ָ�붨λ��������е�2������
			System.out.println(rs.getString("name"));
			System.out.println("��1�����ݵ�nameֵ�ǣ�");
			rs.beforeFirst(); //��ָ�붨λ��������е�һ������֮ǰ
			rs.next(); //��ָ��������
			System.out.println(rs.getString("name"));
			System.out.println("��4�����ݵ�nameֵ�ǣ�");
			rs.afterLast(); //��ָ�붨λ������������һ������֮��
			rs.previous(); //��ָ����ǰ����
			System.out.println(rs.getString("name"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally { //�ͷ���Դ
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
