package _servlet.getFiles;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReceiveFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		InputStream in = req.getInputStream();
		FileOutputStream out = new FileOutputStream( 
				req.getSession().getServletContext().getRealPath("/")
			  + "/_resource/xx.jpg");
		byte[] buff = new byte[1024];
		int len = 0;
		while( (len=in.read(buff)) != -1 ) {
			out.write(buff, 0, len);
		}
		in.close();
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
