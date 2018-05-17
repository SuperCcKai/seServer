package _servlet.loginSystem;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import _bean.loginSystem.Student;
import _dao.loginSystem.StudentDAO;
import _utils._servlet.ServletUtil;
import _utils.loginSystem.OtherUtils;
import _utils.loginSystem.RedisUtils;
import _utils.myBatis.MybatisUtils;

/**
 * ѧ����¼
 * @author supercckai
 *
 */
public class LoginOfStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory sqlSessionFactory = 
			MybatisUtils.getSessionFactory();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Log log = LogFactory.getLog(LoginOfStuServlet.class);
		//���û�ȡ���뷽ʽ
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		try {
			//��ȡapp��json
			String jsonStr = ServletUtil.getReqBody(req);
			System.out.println("jsonStr: " + jsonStr);
			//����json
			JsonParser jParse = new JsonParser(); //����json������
			JsonObject jsonObj = (JsonObject)jParse.parse(jsonStr);
			//��ȡstudent����
			String lStuID = jsonObj.get("stuID").getAsString();
			String lStuPassword = jsonObj.get("password").getAsString();
			System.out.println("lStuID: " + lStuID);
			System.out.println("lStuPassword: " + lStuPassword);
			log.debug("#################lStuID: " + lStuID + "##########");
			//��ѯ��student
			String password = getStudent(lStuID);
			if(password == null) {
				//resp.getWriter().write("�ʺ�δע��");
				resp.getWriter().write("no_stuID");
			}else {
				System.out.println("��¼���룺" + lStuPassword);
				System.out.println("ʵ�����룺" + password);
				if(password.equals(lStuPassword) ) {
					//resp.getWriter().write("ѧ����¼�ɹ�!");
					resp.getWriter().write("yes");
					//����cookie��name��value
					String name = lStuID;
					String value = lStuID + lStuPassword;
					value = OtherUtils.MD5(value); //MD5����
					Timestamp time = new Timestamp(new Date().getTime() );
					value += time.toString();
					value = value.replaceAll(" ", "");
					System.out.println("cookie_name: " + name 
							+ ", cookie_value: " + value);
					//����cookie
					Cookie cookie = new Cookie(name, value);
					//��¼��Ϣ����redis
					RedisUtils.setLoginMess(name, value);
					//����cookie
					resp.addCookie(cookie);
				}
				else {
					//resp.getWriter().write("ѧ���������!");
					resp.getWriter().write("error_password");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString() );
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	//��������mybatis����
	private String getStudent(String stuID_tmp) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
		Student student = studentDAO.getStudentById(stuID_tmp);
		//�ύ
		sqlSession.commit();
		if(student == null) {
			return null;
		}else {
			System.out.println("stuName: " + student.getStuName() );
			return student.getStuPassword();
		}
	}
	
}