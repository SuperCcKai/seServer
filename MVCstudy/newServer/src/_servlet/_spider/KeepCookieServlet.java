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
		//定义即将访问的链接
		String urlStr = "https://zhjw.neu.edu.cn/ACTIONLOGON.APPPROCESS?"
				+ "mode=1&applicant=ACTIONQUERYSTUDENTSCHEDULEBYSELF";
		//创建一个HttpClientContext对象，用来保存cookie
		HttpClientContext httpClientContext = HttpClientContext.create();
		try {
			//URL url = new URL(urlStr);
			//构造自定义Header信息
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
		    //构造请求对象
		    HttpUriRequest httpUriRequest = RequestBuilder.get()
		    		.setUri(urlStr).build();
		    //执行请求，传入HttpContext，将会得到请求结果的信息
		    httpClient.execute(httpUriRequest, httpClientContext);
			//从请求结果中获取cookie
		    CookieStore cookieStore = httpClientContext.getCookieStore();
		    //这个cookieStore保存了我们的信息，先将它保存到本地文件
		    CookieUtil.saveCookieStore(cookieStore, request, "willLoginCookie");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
