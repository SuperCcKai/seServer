package _servlet.labelMng;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import _bean.loginSystem.Teacher;
import _dao.loginSystem.TeacherDAO;
import _utils.myBatis.MybatisUtils;

/**
 * 从数据库得到所有标签信息
 * @author supercckai
 *
 */
public class GetLabelsOfAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory sqlSessionFactory = 
			MybatisUtils.getSessionFactory();
	Teacher teacher = new Teacher();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置获取编码方式
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		try {
			//获取user属性
			String rTeaID = req.getParameter("teaID");
			String rTeaName = req.getParameter("username");
			//rTeaName = new String(rTeaName.getBytes("utf-8"), "gbk");
			String rTeaPassword = req.getParameter("password");
			System.out.println("rTeaName: " + rTeaName);
			//设置user属性
			teacher.setTeaID(rTeaID);
			teacher.setTeaName(rTeaName);
			teacher.setTeaPassword(rTeaPassword);
			//存入数据库的表teacher中
			insertTeacher(teacher);
			resp.getWriter().write("教师注册成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	//具体连接mybatis方法
	private void insertTeacher(Teacher teacher) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TeacherDAO teacherDAO = sqlSession.getMapper(TeacherDAO.class);
		teacherDAO.insertTeacher(teacher);
		//提交
		sqlSession.commit();
	}
	
}
