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
 * �����ݿ�õ����б�ǩ��Ϣ
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
		
		//���û�ȡ���뷽ʽ
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		try {
			//��ȡuser����
			String rTeaID = req.getParameter("teaID");
			String rTeaName = req.getParameter("username");
			//rTeaName = new String(rTeaName.getBytes("utf-8"), "gbk");
			String rTeaPassword = req.getParameter("password");
			System.out.println("rTeaName: " + rTeaName);
			//����user����
			teacher.setTeaID(rTeaID);
			teacher.setTeaName(rTeaName);
			teacher.setTeaPassword(rTeaPassword);
			//�������ݿ�ı�teacher��
			insertTeacher(teacher);
			resp.getWriter().write("��ʦע��ɹ�!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	//��������mybatis����
	private void insertTeacher(Teacher teacher) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TeacherDAO teacherDAO = sqlSession.getMapper(TeacherDAO.class);
		teacherDAO.insertTeacher(teacher);
		//�ύ
		sqlSession.commit();
	}
	
}
