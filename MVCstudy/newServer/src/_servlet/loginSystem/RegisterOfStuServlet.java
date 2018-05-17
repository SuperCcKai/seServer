package _servlet.loginSystem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import _bean.loginSystem.Student;
import _dao.loginSystem.StudentDAO;
import _utils._servlet.ServletUtil;
import _utils.myBatis.MybatisUtils;

/**
 * 接收学生注册信息并存入数据库
 * @author supercckai
 *
 */
public class RegisterOfStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory sqlSessionFactory = 
			MybatisUtils.getSessionFactory();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Student student = new Student();
		//设置获取编码方式
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		try {
			//获取app端json
			String jsonStr = ServletUtil.getReqBody(req);
			System.out.println("jsonStr: " + jsonStr);
			//解析json
			JsonParser jParse = new JsonParser(); //创建json解析器
			JsonObject jsonObj = (JsonObject)jParse.parse(jsonStr);
			//获取student属性
			String rStuID = jsonObj.get("stuID").getAsString();
			String rStuName = jsonObj.get("username").getAsString();
			//rTeaName = new String(rTeaName.getBytes("utf-8"), "gbk");
			String rStuPassword = jsonObj.get("password").getAsString();
			System.out.println("rStuID: " + rStuID);
			System.out.println("rTeaName: " + rStuName);
			System.out.println("rStuPassword: " + rStuPassword);
			//设置user属性
			student.setStuID(rStuID);
			student.setStuName(rStuName);
			student.setStuPassword(rStuPassword);
			//存入数据库的表teacher中
			insertStudent(student);
			//resp.getWriter().write("学生注册成功!");
			resp.getWriter().write("yes");
		} catch (Exception e) {
			resp.getWriter().write("no");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	//具体连接mybatis方法
	private void insertStudent(Student student) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
		studentDAO.insertStudent(student);
		//提交
		sqlSession.commit();
	}
	
}
