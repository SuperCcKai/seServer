package _utils._servlet;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {
	
	public static String getReqBody(HttpServletRequest req) throws Exception {
		BufferedReader reader = req.getReader();
		String input = null;
		StringBuffer reqBody = new StringBuffer();
		while( (input=reader.readLine() ) != null) {
			reqBody.append(input);
		}
		return reqBody.toString();
	}
	
}
