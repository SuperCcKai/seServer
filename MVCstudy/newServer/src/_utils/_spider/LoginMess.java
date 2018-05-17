package _utils._spider;
/**
 * 爬取页面
 */
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class LoginMess {
	
	public static BufferedImage getImg_old() { //不借助任何jar包
		BufferedImage img = null;
		//定义即将访问的链接
		String urlStr = "https://zhjw.neu.edu.cn/ACTIONLOGON.APPPROCESS?mode=1&"
				+ "applicant=ACTIONQUERYSTUDENTSCHEDULEBYSELF";
		//String action = "ACTIONLOGON.APPPROCESS?mode=";
		//定义一个字符串用来存储网页内容
		@SuppressWarnings("unused")
		String result = "";
		//定义一个缓冲字符输入流
		BufferedReader in = null;
		try {
			URL url = new URL(urlStr);
			//初始化一个链接到那个url的连接
			URLConnection conn = url.openConnection();
			//开始实际的连接
			conn.connect();
			//初始化BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream() ) );
			//用来临时存储抓取到的每一行的数据
			String line;
			while( (line=in.readLine()) != null ) {
				//遍历抓取到的每一行并将其存储到result里面
				result += line + "\n";
			}//while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭输入流
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(result);
		return img;
	}
	
	public static void prePareLogin() {
		//定义即将访问的链接
		String urlStr = "https://zhjw.neu.edu.cn/ACTIONVALIDATERANDOMPICTURE"
				+ ".APPPROCESS?id=0.4607196975898509";
		InputStream in = null;
		OutputStream out = null;
		try {
			URL url = new URL(urlStr);
			//初始化一个链接到那个url的连接
			URLConnection conn = url.openConnection();
			//开始实际的连接
			//conn.connect();
			
			//得到url的输入流
			in = conn.getInputStream();
			//设置数据缓冲
			byte[] buff = new byte[1024];
			//读取到的数据长度
			int len;
			//输出的文件流保存图片至本地
			out = new FileOutputStream(
					"WebRoot/_resource/img/checkCode.jpg");
			while( (len=in.read(buff) ) != -1) {
				out.write(buff, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭输入流
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(result);
	}

	public static void callKeepCookieServlet() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(
				"http://localhost:8080/newServer/keepCookie");
		try {
			/*
			URL url = new URL("http://localhost:8080/newServer/loginAAO");
			URLConnection conn = url.openConnection();
			conn.connect();
			*/
			httpClient.execute(httpGet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
		}
	}
	
	public static void callLoginMessServlet() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(
				"http://localhost:8080/newServer/loginMess");
		try {
			/*
			URL url = new URL("http://localhost:8080/newServer/loginAAO");
			URLConnection conn = url.openConnection();
			conn.connect();
			*/
			httpClient.execute(httpGet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
		}
	}
	
	@Test
	public void test() {
		//prePareLogin();
		callLoginMessServlet();
	}
	
}
