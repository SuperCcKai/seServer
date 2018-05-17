package _dao.loginSystem;

import _bean.loginSystem.Student;

public interface StudentDAO {
	public void insertStudent(Student student); //����ѧ����Ϣ
	public void deleteStudent(String stuID); //ɾ��ѧ����Ϣ
	public void updateStudent(Student student); //����ѧ����Ϣ
	public Student getStudentById(String stuID); //��ȡѧ����Ϣ
}
