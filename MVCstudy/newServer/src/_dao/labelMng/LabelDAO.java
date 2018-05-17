package _dao.labelMng;
/**
 * 标签系统的方法
 * @author supercckai
 *
 */
public interface LabelDAO {
	public void getLabelsOfAll(); //查看所有标签
	public void getLabelsOfOne(); //查看某个学生的所有标签
	public void getStusOfLabel(); //查看具有某个标签的所有学生
	public void addLabelInSys(); //在系统标签库里添加标签
	public void addLabelOfOne(); //为某个学生添加一个标签
	public void delLabelOfOne(); //删除某个学生的某个标签
	//删除某个标签，原来有此标签的学生都会被删除此标签
	public void delLabelOfAll();
}
