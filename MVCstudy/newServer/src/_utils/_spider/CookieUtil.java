package _utils._spider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.CookieStore;

/**
 * 保存cookie到本地或读取cookie
 * @author supercckai
 *
 */
public class CookieUtil {
	
	//使用序列化的方式保存CookieStore到本地文件，方便后续的读取使用
	public static void saveCookieStore(CookieStore cookieStore, HttpServletRequest req, String name) {
		try {
			FileOutputStream fos = new FileOutputStream(
					req.getSession().getServletContext().getRealPath("/")
				  + "_resource/cookie//" + name );
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(cookieStore);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//读取cookie的序列化文件，读取后可以直接使用
	public static CookieStore readCookieStore(HttpServletRequest req, String name) {
		CookieStore cookieStore = null;
		try {
			FileInputStream fis = new FileInputStream(
					req.getSession().getServletContext().getRealPath("/")
				  + "_resource/cookie/" + name );
			ObjectInputStream in = new ObjectInputStream(fis);
			cookieStore = (CookieStore)in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cookieStore;
	}
	
}
