package _servlet._spider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		FileInputStream fis = new FileInputStream(
				req.getSession().getServletContext().getRealPath("/")
				  + "_resource/result/schedule" );
		InputStreamReader isr = new InputStreamReader(fis, "gbk");
		BufferedReader in = new BufferedReader(isr);
		//result存储读取到的内容
		String html = "";
		String line = "";
		while( (line=in.readLine()) != null ) {
			html += line + "\n";
		}
		Document doc = Jsoup.parse(html);
		//<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class=tableborder>
		//<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		//<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
		//<table border="0" align="center" cellpadding="0" cellspacing="0" frame="box">
		Elements tables = doc.select("table[align]");
		Element table = tables.first();
		Elements tds = table.select("td[valign='top']");
		for(Element td : tds) {
			String str = td.text();
			str = "#" + str + "#";
			System.out.println(str);
		}
		//关闭
		in.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
