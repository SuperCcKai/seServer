package _utils.loginSystem;

import javax.servlet.http.Cookie;

import redis.clients.jedis.Jedis;

public class RedisUtils {
	
	public static void setLoginMess(String name, String value) {
		//���ӵ����ص�redis����
		Jedis jedis = new Jedis("127.0.0.1");
		System.out.println("redis���ӳɹ�");
		jedis.set(name, value);
		jedis.close();
	}
	
	public static boolean loginCheck(Cookie cookie) {
		//���ӵ����ص�redis����
		Jedis jedis = new Jedis("127.0.0.1");
		System.out.println("redis���ӳɹ�");
		String name = cookie.getName();
		String value = cookie.getValue();
		System.out.println("cookie_name:" + name + " ,cookie_value:" + value);
		System.out.println("jedis_value:" + jedis.get(name) );
		if(value.equals(jedis.get(name) ) ) {
			return true;
		}
		return false;
	}
	
}
