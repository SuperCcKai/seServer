package _dao.labelMng;
/**
 * ��ǩϵͳ�ķ���
 * @author supercckai
 *
 */
public interface LabelDAO {
	public void getLabelsOfAll(); //�鿴���б�ǩ
	public void getLabelsOfOne(); //�鿴ĳ��ѧ�������б�ǩ
	public void getStusOfLabel(); //�鿴����ĳ����ǩ������ѧ��
	public void addLabelInSys(); //��ϵͳ��ǩ������ӱ�ǩ
	public void addLabelOfOne(); //Ϊĳ��ѧ�����һ����ǩ
	public void delLabelOfOne(); //ɾ��ĳ��ѧ����ĳ����ǩ
	//ɾ��ĳ����ǩ��ԭ���д˱�ǩ��ѧ�����ᱻɾ���˱�ǩ
	public void delLabelOfAll();
}
