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
	//��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ��
	private static int onLineCount = 0;
	//�����½���û���ʶ
	private static Map<Session, String> webSocketMap = new HashMap<Session, String>();
	//�û��ı�ʶ
	private Session session;
	
	@OnOpen
	public void onOpen(@PathParam(value="param")String param, Session session, EndpointConfig config) {
		System.out.println("server open");
		//����Session
		this.session = session;
		//����ǰ���ӱ��浽Map��
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
				//��websocket��������ֵ���ػ�ȥ
				session.getBasicRemote().sendText(msg);
				//����ʦ������Ϣ����ѧ��
				
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