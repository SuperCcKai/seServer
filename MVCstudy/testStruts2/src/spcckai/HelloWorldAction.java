package spcckai;

public class HelloWorldAction extends com.opensymphony.xwork2.ActionSupport {
	
	@Override
	public String execute() throws Exception {
		System.out.println("����ִ�е�HelloWorldAction");
		//������ͼsuccess�����ǿ�ܶ���
		return SUCCESS;
	}
	
}
