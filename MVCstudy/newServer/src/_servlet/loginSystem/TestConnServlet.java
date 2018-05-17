package _servlet.loginSystem;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

public class TestConnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JsonObject user = new JsonObject();
		user.addProperty("username", "pk");
		user.addProperty("password", "spcckai");
		String content = String.valueOf(user);
		System.out.println(content);
		String url = "http://127.0.0.1:8080/newServer/register";
		HttpURLConnection conn = (HttpURLConnection)(new URL(url)
				.openConnection() );
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Charset", "utf-8");
		OutputStream os = conn.getOutputStream();
		os.write(content.getBytes());
		os.close();
		//
		try {
			String result = read(conn.getInputStream());
			System.out.println("TestResult: " + result);
			if(result.equals("1"))
				System.out.println("SUCCESS!");
			else
				System.out.println("FAILED!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private String read(InputStream inputStream) throws Exception {
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		String str = "";
		byte[] buf = new byte[1024];
		int length = 0;
		while( (length=bis.read(buf) ) != -1) {
			str += new String(buf, 0, length);
		}
		return str;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
