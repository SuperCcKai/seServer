package testOkhttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * okhttp获取cookie
 * @author supercckai
 *
 */
public class testCookie {
	
	@Test
	public void test() {
		CookieJar cookieJar = new CookieJar() {
			//cookie缓存区
			private final Map<String, List<Cookie>> cookiesMap = new 
					HashMap<String, List<Cookie> >();
			@Override
			public void saveFromResponse(HttpUrl arg0, List<Cookie> arg1) {
				System.out.println("saveCookie");
				//移除相同的url的cookie
				String host = arg0.host();
				cookiesMap.put(host, arg1);
				for(Cookie cookie:arg1) {
					if(cookie.name().equals("20154445") ) {
						System.out.println("cookie已获取，值为：");
						System.out.println(cookie.value() );
					}
				}
			}
			
			@Override
			public List<Cookie> loadForRequest(HttpUrl arg0) {
				List<Cookie> cookiesList = cookiesMap.get(arg0.host() );
				return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
			}
		};
		//建立OkHttpClient
		OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5000, 
				TimeUnit.MILLISECONDS).cookieJar(cookieJar).build();
		//设置json
		String jsonStr = "{ \n \"stuID\":\"20154445\", "
				+ "\n \"password\":\"123456\"}";
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, jsonStr);
		//创建登录的表单
		/*
		FormBody formBody = new FormBody.Builder()
				.add("username", "庞凯")
				.add("password", "123456")
				.build();
		FormBody formBody = new FormBody.Builder().build();
		*/
		String urlStr = "http://58.87.90.180:8080/newServer/LoginOfStu";
		//创建request请求
		Request req = new Request.Builder()
					.url(urlStr)
					.post(body)
					.build();
		//上传
		Call call = client.newCall(req);
		try {
			//非异步执行
			Response resp = call.execute();
			//测试是否登录成功
			System.out.println(resp.body().string() );
			//获取返回数据的头部
			Headers headers = resp.headers();
			HttpUrl url = req.url();
			//获取头部的cookie
			List<Cookie> cookies = Cookie.parseAll(url, headers);
			//防止header没有Cookie的情况
			if(cookies != null) {
				/*
				//存储到cookie管理器中
				client.cookieJar().saveFromResponse(url, cookies);
				*/
				for(Cookie cookie:cookies) {
					if(cookie.name().equals("20154445") ) {
						System.out.println("cookie已获取，值为：");
						System.out.println(cookie.value() );
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
