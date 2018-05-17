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
 * ��һ����������SQL����ִ��ʹ�ù�Statement����ʵ�ֵģ�Statement����ÿ��ִ��SQL
 * 		���ʱ�����������б��룻����ͬ��SQL���ִ�ж��ʱ��Statement����ͻ�ʹ��
 * 		�ݿ�Ƶ��������ͬ��SQL��䣬�Ӷ��������ݿ�ķ���Ч�ʡ�
 * 
 * ����������⣬���õ���PrepareStatement��������Զ�SQL������Ԥ���롣
 * 
 * Ҳ����˵������ͬ��SQL����ٴ�ִ��ʱ�����ݿ�ֻ��ʹ�û������е����ݣ�������Ҫ��SQL
 * 		����ٴα��룬�Ӷ���Ч������ݵķ���Ч�ʡ�
 * 
 * �˳��������PrepareStatement����Ļ���ʹ��
 * @author supercckai
 *
 */
public class TestPreparedStmt {
	
	@Test
	public void test() {
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
			//�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/chapter01"
					+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String username = "root";
			String password = "spcckai";
			//����Ӧ�ó��������ݿ����ӵ�Connection����
			conn = DriverManager.getConnection(url, username, password);
			//ִ�е�sql���
			String sql = "INSERT INTO users(name, password, email, birthday)"
					+ "VALUES(?, ?, ?, ?)";
			//����ִ��sql����PreparedStatement����
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, "zl");
			preStmt.setString(2, "123456");
			preStmt.setString(3, "zl@sina.com");
			preStmt.setString(4, "1789-12-23");
			preStmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally { //�ͷ���Դ
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
