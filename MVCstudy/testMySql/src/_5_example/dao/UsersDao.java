package _5_example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import _5_example.domain.User;
import _5_example.utils.JDBCUtils;

/**
 * ��װ�˶Ա�users����ӡ���ѯ��ɾ���͸��µȲ���
 * @author supercckai
 *
 */
public class UsersDao {
	
	//����û��Ĳ���
	public boolean insert(User user) {
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//������ݿ�����
			conn = JDBCUtils.getConnection();
			//���Statement����
			stmt = conn.createStatement();
			//����sql���
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			String birthday = sdf.format(user.getBirthday());
			String sql = 
					"INSERT INTO users(id, name, password, email, birthday) "
					+ "VALUES(" + user.getId() + ",'" + user.getUsername()
					+ "','" + user.getPassword() + "','" + user.getEmail()
					+ "','" + birthday+ "');";
			int num = stmt.executeUpdate(sql);
			if(num > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}
	
	//��ѯ���е�User����
	public ArrayList<User> findAll() {
		
		
		return null;
	}
	
}
