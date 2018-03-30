package com.pentaKill.chat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.pentaKill.domain.ChatLogBean;
import com.pentaKill.domain.ConversationBean;
import com.pentaKill.domain.FindConversationBean;
import com.pentaKill.service.ConversationService;

import net.sf.json.JSONObject;

@ServerEndpoint("/serve")
public class WebSocketServer {
	@Resource 
	ConversationService conversationService;
	
	private boolean firstTime = true;
	private String nickName;
	private static HashMap<String, Object> connectedUser = new HashMap<String, Object>();
	private static HashMap<String, String> userMap = new HashMap<String, String>();
	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		connectedUser.put(session.getId(), this);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WebSocketServer webSocketServer = null;
		JSONObject json = JSONObject.fromObject(message);
		String nickname = json.getString("nickname");
		String sender_id = json.getString("sender_id");
		String receiver_id = json.getString("receiver_id");
		String content=json.getString("content");
		
		if (firstTime) {
			
			//第一次打开窗口要发送用户名进来
			
			//第一次要开启会话，记录会话的开始时间(发送者是客服的时候)
			if(Integer.parseInt(sender_id)<2000){
				ConversationBean cb=new ConversationBean(Integer.parseInt(receiver_id),Integer.parseInt(sender_id),null,null,-1);
				conversationService.insertConversation_service(cb);
			}
			
			userMap.put(session.getId(),nickname);
			
			firstTime = false;
		} else {
			//每次会话都要存入数据库
			int customer_id=-1;
			int cs_id=-1;
			int from_customer=0;
			int conversation_id=-1;
			int content_type=0;
			
			//判断发送者是客服还是用户
			if(Integer.parseInt(sender_id)<2000){
				from_customer=1;
				cs_id=Integer.parseInt(sender_id);
				customer_id=Integer.parseInt(receiver_id);
			}else{
				customer_id=Integer.parseInt(sender_id);
				cs_id=Integer.parseInt(receiver_id);
			}
			
			FindConversationBean fcb=new FindConversationBean(customer_id,cs_id);
			conversation_id=conversationService.findConversationId(fcb);
			
			ChatLogBean clb=new ChatLogBean(conversation_id,Integer.parseInt(receiver_id),Integer.parseInt(sender_id),from_customer,null,content_type,content);
			conversationService.insertChatLog_service(clb);
			
			json.put("date", df.format(new Date()));

			for (String key : userMap.keySet()) {
				webSocketServer = (WebSocketServer) connectedUser.get(key);
				if (nickname.equalsIgnoreCase(userMap.get(key))) {

					json.put("isSelf", true);
					synchronized (webSocketServer) {
						webSocketServer.session.getAsyncRemote().sendText(json.toString());
					}

				} else if (key.equals(receiver_id)) {
					json.put("isSelf", false);
					synchronized (webSocketServer) {
						webSocketServer.session.getAsyncRemote().sendText(json.toString());

					}
				}

			}
		}
	}

	@OnClose
	public void onClose(String message, Session session) {
		//怎么显示在页面上
		// 在客户端关闭的时候发送receiver_id
		/*
		 * JSONObject json = JSONObject.fromObject(message);
		 * 
		 * String receiver_id = json.getString("receiver_id");
		 * 
		 * String toMessage = "系统：" + userMap.get(session.getId()) + "退出会话";
		 * json.replace("content", toMessage);
		 */
		//关闭会话后，要更新会话的结束时间
		userMap.remove(session.getId());
		connectedUser.remove(session.getId());

	}
	@OnError
	public void onError(Throwable t){
		
	}

}
