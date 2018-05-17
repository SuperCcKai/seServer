package _dao.loginSystem;

import _bean.loginSystem.Teacher;

public interface TeacherDAO {
	public void insertTeacher(Teacher teacher); //���ӽ�ʦ��Ϣ
	public void deleteTeacher(String teaID); //ɾ����ʦ��Ϣ
	public void updateTeacher(Teacher teacher); //���½�ʦ��Ϣ
	public Teacher getTeacherById(String teaID); //��ȡ��ʦ��Ϣ
}
