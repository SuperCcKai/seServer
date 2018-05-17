package _servlet._spider._ownSchedule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import _utils._spider.CookieUtil;

public class LoginMessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//BufferedImage img = null;
		//���弴�����ʵ�����
		String urlStr = "https://zhjw.neu.edu.cn/ACTIONVALIDATERANDOMPICTURE"
				+ ".APPPROCESS?id=0.4607196975898509";
		InputStream in = null;
		OutputStream out = null;
		CookieStore readCookieStore = CookieUtil.readCookieStore(request, "willLoginCookie");
		try {
			/*
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
			//������ļ�������ͼƬ���������˱���
			out = new FileOutputStream( 
					request.getSession().getServletContext().getRealPath("/")
				  + "/_resource/img/checkCode.jpg");
			while( (len=in.read(buff) ) != -1) {
				out.write(buff, 0, len);
			}
			*/
			CloseableHttpClient httpClient = HttpClientBuilder.create()
					.setDefaultCookieStore(readCookieStore).build();
			HttpGet httpGet = new HttpGet(urlStr);
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			//������ļ�������ͼƬ���������˱���
			out = new FileOutputStream( 
					request.getSession().getServletContext().getRealPath("/")
				  + "/_resource/img/checkCode.jpg");
			//�õ�������Դ���ֽ�����
			HttpEntity entity = httpResponse.getEntity();
			if(entity != null) {
				in = entity.getContent();
				//��������
				byte[] buff = new byte[1024];
				int len = 0;
				while( (len=in.read(buff)) != -1 ) {
					out.write(buff, 0, len);
				}
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}