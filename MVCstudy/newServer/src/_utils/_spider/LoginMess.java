package _utils._spider;
/**
 * ��ȡҳ��
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
	
	public static BufferedImage getImg_old() { //�������κ�jar��
		BufferedImage img = null;
		//���弴�����ʵ�����
		String urlStr = "https://zhjw.neu.edu.cn/ACTIONLOGON.APPPROCESS?mode=1&"
				+ "applicant=ACTIONQUERYSTUDENTSCHEDULEBYSELF";
		//String action = "ACTIONLOGON.APPPROCESS?mode=";
		//����һ���ַ��������洢��ҳ����
		@SuppressWarnings("unused")
		String result = "";
		//����һ�������ַ�������
		BufferedReader in = null;
		try {
			URL url = new URL(urlStr);
			//��ʼ��һ�����ӵ��Ǹ�url������
			URLConnection conn = url.openConnection();
			//��ʼʵ�ʵ�����
			conn.connect();
			//��ʼ��BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(conn.getInputStream() ) );
			//������ʱ�洢ץȡ����ÿһ�е�����
			String line;
			while( (line=in.readLine()) != null ) {
				//����ץȡ����ÿһ�в�����洢��result����
				result += line + "\n";
			}//while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//�ر�������
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
		//���弴�����ʵ�����
		String urlStr = "https://zhjw.neu.edu.cn/ACTIONVALIDATERANDOMPICTURE"
				+ ".APPPROCESS?id=0.4607196975898509";
		InputStream in = null;
		OutputStream out = null;
		try {
			URL url = new URL(urlStr);
			//��ʼ��һ�����ӵ��Ǹ�url������
			URLConnection conn = url.openConnection();
			//��ʼʵ�ʵ�����
			//conn.connect();
			
			//�õ�url��������
			in = conn.getInputStream();
			//�������ݻ���
			byte[] buff = new byte[1024];
			//��ȡ�������ݳ���
			int len;
			//������ļ�������ͼƬ������
			out = new FileOutputStream(
					"WebRoot/_resource/img/checkCode.jpg");
			while( (len=in.read(buff) ) != -1) {
				out.write(buff, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//�ر�������
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
