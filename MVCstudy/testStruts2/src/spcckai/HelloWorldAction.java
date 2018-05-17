package spcckai;

public class HelloWorldAction extends com.opensymphony.xwork2.ActionSupport {
	
	@Override
	public String execute() throws Exception {
		System.out.println("正在执行的HelloWorldAction");
		//返回视图success，这是框架定义
		return SUCCESS;
	}
	
}
