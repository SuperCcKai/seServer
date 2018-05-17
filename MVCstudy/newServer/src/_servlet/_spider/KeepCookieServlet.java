package _servlet._spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import _utils._spider.*;

public class KeepCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���弴�����ʵ�����
		String urlStr = "https://zhjw.neu.edu.cn/ACTIONLOGON.APPPROCESS?"
				+ "mode=1&applicant=ACTIONQUERYSTUDENTSCHEDULEBYSELF";
		//����һ��HttpClientContext������������cookie
		HttpClientContext httpClientContext = HttpClientContext.create();
		try {
			//URL url = new URL(urlStr);
			//�����Զ���Header��Ϣ
		    List<Header> headerList = new ArrayList<Header>();
		    headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9," +
		            "image/webp,image/apng,*/*;q=0.8"));
		    headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
		            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"));
		    headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
		    headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
		    headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
		    headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2," +
		           "de;q=0.2"));
		    CloseableHttpClient httpClient = HttpClientBuilder.create()
		    		.setDefaultHeaders(headerList).build();
		    //�����������
		    HttpUriRequest httpUriRequest = RequestBuilder.get()
		    		.setUri(urlStr).build();
		    //ִ�����󣬴���HttpContext������õ�����������Ϣ
		    httpClient.execute(httpUriRequest, httpClientContext);
			//���������л�ȡcookie
		    CookieStore cookieStore = httpClientContext.getCookieStore();
		    //���cookieStore���������ǵ���Ϣ���Ƚ������浽�����ļ�
		    CookieUtil.saveCookieStore(cookieStore, request, "willLoginCookie");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
