package _servlet.loginSystem;


import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * 测试学生注册
 * @author supercckai
 *
 */
public class _TestLoginOfStu {
	
	@Test
	public void test() throws Exception {
		String urlStr = "http://58.87.90.180:8080/newServer/LoginOfStu";
		String jsonStr = "{ \n \"stuID\":\"20154445\", "
				+ "\n \"password\":\"257475\"}";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		//创建一个HttpClientContext对象，用来保存cookie
		HttpClientContext httpClientContext = HttpClientContext.create();
		HttpPost httpPost = new HttpPost(urlStr);
		StringEntity stringEntity = new StringEntity(jsonStr, ContentType
				.create("text/json", "utf-8") );
		httpPost.setEntity(stringEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost, httpClientContext);
		//获取网页内容
		HttpEntity respEntity = httpResponse.getEntity();
		String result = EntityUtils.toString(respEntity, "utf-8");
		System.out.println(result);
		//获取cookie
		CookieStore cookieStore = httpClientContext.getCookieStore();
		List<Cookie> cookies = cookieStore.getCookies();
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("20154445") ) {
				System.out.println("cookie已获取，值为：");
				System.out.println(cookie.getValue() );
				break;
			}
		}
		
		
		
	}
	
	
}
