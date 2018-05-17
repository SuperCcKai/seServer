package _servlet.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocket/server/{param}")
public class SocketServer {
	//静态变量，用来记录当前在线连接数，应该把它设计成线程安全的
	private static int onLineCount = 0;
	//保存登陆的用户标识
	private static Map<Session, String> webSocketMap = new HashMap<Session, String>();
	//用户的标识
	private Session session;
	
	@OnOpen
	public void onOpen(@PathParam(value="param")String param, Session session, EndpointConfig config) {
		System.out.println("server open");
		//保存Session
		this.session = session;
		//将当前连接保存到Map中
		webSocketMap.put(session, param);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("server close");
		webSocketMap.remove(session);
	}
	
	@OnMessage
	public void onMessage(Session session, String msg) {
		System.out.println("send message: " + msg);
		if(session.isOpen() ) {
			try {
				//将websocket传过来的值返回回去
				session.getBasicRemote().sendText(msg);
				//将老师发的消息发给学生
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("server close");
		webSocketMap.remove(session);
	}
	
}
