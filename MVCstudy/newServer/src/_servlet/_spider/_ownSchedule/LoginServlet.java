package _servlet._spider._ownSchedule;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import _utils._spider.CookieUtil;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String urlStr = "https://zhjw.neu.edu.cn/ACTIONLOGON.APPPROCESS?mode=";
		//读取cookie
		CookieStore readCookieStore = CookieUtil.readCookieStore(req, "willLoginCookie");
		//创建带有cookie的httpClient
		CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setDefaultCookieStore(readCookieStore).build();
		//创建HttpPost
		HttpPost httpPost = new HttpPost(urlStr);
		//创建参数队列
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("WebUserNO", 
				req.getParameter("WebUserNO")) );
		formParams.add(new BasicNameValuePair("Password", 
				req.getParameter("Password")) );
		formParams.add(new BasicNameValuePair("Agnomen", 
				req.getParameter("Agnomen")) );
		UrlEncodedFormEntity uefEntity;
		System.out.println("username: " + req.getParameter("WebUserNO") );
		System.out.println("password: " + req.getParameter("Password") );
		System.out.println("checkCode: " + req.getParameter("Agnomen") );
		//定义一个String对象存储网页内容
		String result = null;
		try {
			//编码并打包
			uefEntity = new UrlEncodedFormEntity(formParams, "utf-8");
			httpPost.setEntity(uefEntity);
			//创建HttpContext对象
			HttpClientContext httpContext = HttpClientContext.create();
			//获得响应
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost, httpContext);
			System.out.println( "status: " + httpResponse.getStatusLine() );
			//获取网页内容
			HttpEntity respEntity = httpResponse.getEntity();
			result = EntityUtils.toString(respEntity, "utf-8");
			FileWriter out = new FileWriter(
					req.getSession().getServletContext().getRealPath("/")
				  + "_resource/result/alreadyLogin" );
			out.write(result);
			out.close();
			httpResponse.close();
			httpClient.close();
			//
			//获取课程表
			//https://zhjw.neu.edu.cn/ACTIONQUERYSTUDENTSCHEDULEBYSELF.APPPROCESS
			CookieStore loginCookieStore = httpContext.getCookieStore();
			CookieUtil.saveCookieStore(loginCookieStore, req, "alreadyLoginCookie");
			String urlStr2 = "https://zhjw.neu.edu.cn/ACTIONQUERYSTUDENTSCHEDULEBYSELF.APPPROCESS";
			CloseableHttpClient httpClient2 = HttpClientBuilder.create()
					.setDefaultCookieStore(loginCookieStore).build();
			HttpPost httpPost2 = new HttpPost(urlStr2);
			CloseableHttpResponse httpResponse2 = httpClient2.execute(httpPost2);
			HttpEntity entity = httpResponse2.getEntity();	
			result = EntityUtils.toString(entity, "gbk");
			FileWriter out2 = new FileWriter(
					req.getSession().getServletContext().getRealPath("/")
				  + "_resource/result/schedule" );
			out2.write(result);
			out2.close();
			httpResponse2.close();
			httpClient2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
