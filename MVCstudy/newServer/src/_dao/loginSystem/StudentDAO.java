package _dao.loginSystem;

import _bean.loginSystem.Student;

public interface StudentDAO {
	public void insertStudent(Student student); //增加学生信息
	public void deleteStudent(String stuID); //删除学生信息
	public void updateStudent(Student student); //更新学生信息
	public Student getStudentById(String stuID); //获取学生信息
}
