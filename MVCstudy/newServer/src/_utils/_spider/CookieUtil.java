package _utils._spider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.CookieStore;

/**
 * ����cookie�����ػ��ȡcookie
 * @author supercckai
 *
 */
public class CookieUtil {
	
	//ʹ�����л��ķ�ʽ����CookieStore�������ļ�����������Ķ�ȡʹ��
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
	
	//��ȡcookie�����л��ļ�����ȡ�����ֱ��ʹ��
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
