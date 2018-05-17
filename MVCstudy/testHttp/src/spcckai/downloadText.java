package spcckai;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class downloadText {
	
	@Test
	public void test() throws Exception {
		BufferedImage img = null;
		//定义即将访问的链接
		String urlStr1 = "https://m.yushuwu.org/novel/35797/";
		//5125865.html
		//int urlStr2 = 5125865;
		int urlStr2 = 7341508;
		String urlStr3 = ".html";
		//定义存储文件名字
		//int download = 1;
		int download = 126;
		FileWriter out = null;
		while(true) {
			//--------------------------------------------------------------/
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.build();
			HttpGet httpGet = new HttpGet( (urlStr1+urlStr2+urlStr3) );
			httpGet.addHeader("Accept", "text/html");
			httpGet.addHeader("Accept-Charset", "utf-8");
			httpGet.addHeader("Accept-Encoding", "gzip");
			httpGet.addHeader("Accept-Language", "en-US,en");
			httpGet.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			//输出的文件流保存图片至服务器端本地
			out = new FileWriter(
					"C:\\Users\\supercckai\\Desktop\\backup\\temp\\" 
					+ download + ".html");
			//得到网络资源的字节数组
			HttpEntity entity = httpResponse.getEntity();
			String result = EntityUtils.toString(entity, "utf-8");
			System.out.println(result);
			out.write(result);
			//--------------------------------------------------------------/
			urlStr2++;
			download++;
			Thread.sleep(5000);
		}//while
	}//test()
	
}
