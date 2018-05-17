package _dao.loginSystem;

import _bean.loginSystem.Teacher;

public interface TeacherDAO {
	public void insertTeacher(Teacher teacher); //增加教师信息
	public void deleteTeacher(String teaID); //删除教师信息
	public void updateTeacher(Teacher teacher); //更新教师信息
	public Teacher getTeacherById(String teaID); //获取教师信息
}
