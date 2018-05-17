package _5_example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import _5_example.domain.User;
import _5_example.utils.JDBCUtils;

/**
 * 封装了对表users的添加、查询、删除和更新等操作
 * @author supercckai
 *
 */
public class UsersDao {
	
	//添加用户的操作
	public boolean insert(User user) {
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//获得数据库连接
			conn = JDBCUtils.getConnection();
			//获得Statement对象
			stmt = conn.createStatement();
			//发送sql语句
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
	
	//查询所有的User对象
	public ArrayList<User> findAll() {
		
		
		return null;
	}
	
}
