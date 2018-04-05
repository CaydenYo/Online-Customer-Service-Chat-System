package com.pentaKill.chat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.pentaKill.domain.ChatLogBean;
import com.pentaKill.domain.ChooseCustomerServiceBean;
import com.pentaKill.domain.ConversationBean;
import com.pentaKill.domain.FindConversationBean;
import com.pentaKill.domain.LastCustomerServiceBean;
import com.pentaKill.domain.NewChatLogBean;
import com.pentaKill.domain.RobotQuestionBean;
import com.pentaKill.service.CSViewsHistoryMessageService;
import com.pentaKill.service.ConversationService;
import com.pentaKill.service.RobotChatService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@ServerEndpoint("/serve")
public class WebSocketServer {
    @Resource
    ConversationService conversationService;
    @Resource
    CSViewsHistoryMessageService csViewsHistoryMessageService;
    @Resource
    RobotChatService robotChatService;

    private final int initvalue = 0;
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
        // 每次都要发送这些东西进来
        String nickname = json.getString("nickname");
        String sender_id = json.getString("sender_id");
        String receiver_id = json.getString("receiver_id");
        String content = json.getString("content");
        String company_name = json.getString("company_name");
        String company_id = json.getString("company_id");

        if (firstTime) {

            // 当用户连接时，首先查询机器人是否开启
            // 如果开启，则加载相应的问题，发送到前端
            // 用户回复数字，查询对应问题的答案，发送回前端
            if (Integer.parseInt(sender_id) >= 2000) {
                int robotFlag = robotChatService.getRobotFlagService(Integer.parseInt(company_id));
                if (robotFlag == 1) {
                    // 查询本公司机器人对应的全部问题
                    List<RobotQuestionBean> rqb = robotChatService
                            .getRobotQuestionService(Integer.parseInt(company_id));

                    for (String key : userMap.keySet()) {
                        webSocketServer = (WebSocketServer) connectedUser.get(key);
                        if (nickname.equalsIgnoreCase(userMap.get(key))) {
                            // 新建一个json对象返回
                            // 不清楚list这样放是否正确
                            JSONObject joTemp = new JSONObject();
                            joTemp.put("nickname", company_name);
                            joTemp.put("date", df.format(new Date()));
                            joTemp.put("isSelf", false);
                            JSONArray jsonArray = new JSONArray();
                            String contentOfRqb = jsonArray.fromObject(rqb).toString();
                            joTemp.put("content", contentOfRqb);
                            synchronized (webSocketServer) {
                                webSocketServer.session.getAsyncRemote().sendText(joTemp.toString());
                            }

                        }
                    }
                } else {
                    // 机器人未开启，将其加入等待队列

                }
            }

            // 第一次打开窗口要发送用户名进来

            // 第一次要开启会话，记录会话的开始时间(发送者是客服的时候)
            if (Integer.parseInt(sender_id) < 2000) {
                ConversationBean cb = new ConversationBean(Integer.parseInt(receiver_id), Integer.parseInt(sender_id),
                        null, null, -1);
                conversationService.insertConversationService(cb);

                // 功能1//customer_waiting_team中包含该cs_id和customer_id的记录remove掉
                conversationService.deleteCustomerWaitingTeam(Integer.parseInt(sender_id),
                        Integer.valueOf(receiver_id));
                // 功能2//客服管理人员查看等待人数要-1
                conversationService.decreaseCsManageToolWaitingPeople();

            } else {
                int cs_id;
                boolean listEmpty = false;
                // 可以继续添加客服的老顾客
                List<ChooseCustomerServiceBean> properLastList = new LinkedList<ChooseCustomerServiceBean>();
                // 新客服且有空列表
                List<ChooseCustomerServiceBean> csList = new LinkedList<ChooseCustomerServiceBean>();
                // 先找上次老客服
                // 老客服且有空列表
                List<LastCustomerServiceBean> lcsList = new LinkedList<LastCustomerServiceBean>();
                lcsList = conversationService.selectLastCustomerService(Integer.parseInt(sender_id));
                if (!lcsList.isEmpty()) {
                    for (LastCustomerServiceBean lcs : lcsList) {
                        ChooseCustomerServiceBean ccs = conversationService
                                .selecLasttCustomerServiceInfo(lcs.getCs_id());
                        if (ccs.getCs_status() == 1 && ccs.getCs_waiting_number() > ccs.getCs_waited_number()) {
                            properLastList.add(ccs);
                        }
                    }
                    if (properLastList.isEmpty()) {
                        listEmpty = true;
                    }
                }

                int distributionType = conversationService.getDistributionType(company_name);

                if (listEmpty) {
                    // 功能1//找到所有在线的客服，找到所有在线客服中未满排队上限数的客服
                    csList = conversationService.selectCustomerServiceByStatus();
                    // 功能2//两种算法 （1）老顾客优先算法去conversation里面找 （2）空闲优先算法选择最多空闲的客服
                    // 2.1 负载分配
                    ChooseCustomerServiceBean temp = csList.get(0);
                    if (distributionType == 0) {
                        for (ChooseCustomerServiceBean ccsb : csList) {
                            if ((ccsb.getCs_waiting_number()
                                    - ccsb.getCs_waited_number()) > (temp.getCs_waiting_number()
                                            - temp.getCs_waited_number())) {
                                temp = ccsb;
                            }
                        }
                    } else {
                        // 2.2轮流分配

                    }
                    cs_id = temp.getCs_id();
                } else {
                    cs_id = properLastList.get(properLastList.size() - 1).getCs_id();
                }
                // 功能3//将该客服的customer_id和被选择cs_id放进到客服等待列表customer_waiting_team中(已写service和mapper)
                conversationService.inserCustomerWaitingTeam(Integer.parseInt(sender_id), cs_id);
                // 功能4 //客服管理人员查看等待人数要+1
                conversationService.increaseCsManageToolWaitingPeople();

            }

            userMap.put(session.getId(), nickname);

            firstTime = false;
        } else if (content.equals("csViewsHistoryMessage.action")) {
            // 当客服点击查看历史信息时，就会发送这个信息"csViewsHistoryMessage.action"
            // 从后台取出所有客服与这个客户的聊天记录，然后发送到前台
            List<NewChatLogBean> ans = csViewsHistoryMessageService.getChatlogService(Integer.parseInt(receiver_id),
                    Integer.parseInt(sender_id));

            Gson gson = new Gson();

            // 需要一个新的js函数来接收
            String temp = gson.toJson(ans);

            // 聊天记录消息只发给自己
            for (String key : userMap.keySet()) {
                webSocketServer = (WebSocketServer) connectedUser.get(key);
                if (nickname.equalsIgnoreCase(userMap.get(key))) {
                    // json.put("isSelf", true);
                    // 判断是不是客服发的直接用from_customer属性判断
                    synchronized (webSocketServer) {
                        webSocketServer.session.getAsyncRemote().sendText(temp);
                    }
                    // 还要根据receiver_id找到对应的nickname
                }
            }

        } else if (content.startsWith("robotAnwser") && Integer.parseInt(sender_id) >= 2000) {
            // 约定前端发进来这个，表示用户想知道机器人问题的答案
            int ansNum = Integer.parseInt(content.substring(11));
            List<RobotQuestionBean> rqb = robotChatService.getRobotQuestionService(Integer.parseInt(company_id));
            String ans = rqb.get(ansNum - 1).getAnwser();

            for (String key : userMap.keySet()) {
                webSocketServer = (WebSocketServer) connectedUser.get(key);
                if (nickname.equalsIgnoreCase(userMap.get(key))) {
                    // 新建一个json对象返回答案
                    JSONObject joTemp = new JSONObject();
                    joTemp.put("nickname", company_name);
                    joTemp.put("date", df.format(new Date()));
                    joTemp.put("isSelf", false);
                    joTemp.put("content", ans);
                    synchronized (webSocketServer) {
                        webSocketServer.session.getAsyncRemote().sendText(joTemp.toString());
                    }

                }
            }

        } else {
            // 每次会话都要存入数据库
            int customer_id = initvalue;
            int cs_id = initvalue;
            int from_customer = initvalue;
            int conversation_id = initvalue;
            int content_type = initvalue;

            String reciver_nickname;

            // 判断发送者是客服还是用户
            if (Integer.parseInt(sender_id) < 2000) {
                from_customer = 1;
                cs_id = Integer.parseInt(sender_id);
                customer_id = Integer.parseInt(receiver_id);
                // nickname为客户的nickname
                reciver_nickname = conversationService.getCustomerNicknameByCustomerId(Integer.valueOf(receiver_id));
            } else {
                customer_id = Integer.parseInt(sender_id);
                cs_id = Integer.parseInt(receiver_id);
                // nickname为客服的nickname
                reciver_nickname = conversationService.getCsNicknameByCsId(Integer.valueOf(receiver_id));
            }

            FindConversationBean fcb = new FindConversationBean(customer_id, cs_id);
            conversation_id = conversationService.findConversationIdService(fcb);

            ChatLogBean clb = new ChatLogBean(conversation_id, Integer.parseInt(receiver_id),
                    Integer.parseInt(sender_id), from_customer, null, content_type, content);
            conversationService.insertChatLogService(clb);

            json.put("date", df.format(new Date()));

            for (String key : userMap.keySet()) {
                webSocketServer = (WebSocketServer) connectedUser.get(key);
                if (nickname.equalsIgnoreCase(userMap.get(key))) {

                    json.put("isSelf", true);
                    synchronized (webSocketServer) {
                        webSocketServer.session.getAsyncRemote().sendText(json.toString());
                    }
                    // 还要根据receiver_id找到对应的nickname
                } else if (key.equals(reciver_nickname)) {
                    json.put("isSelf", false);
                    synchronized (webSocketServer) {
                        webSocketServer.session.getAsyncRemote().sendText(json.toString());

                    }
                }

            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        // 怎么显示在页面上
        // 在客户端关闭的时候发送receiver_id
        /*
         * JSONObject json = JSONObject.fromObject(message);
         * 
         * String receiver_id = json.getString("receiver_id");
         * 
         * String toMessage = "系统：" + userMap.get(session.getId()) + "退出会话";
         * json.replace("content", toMessage);
         */
        // 关闭会话后，要更新会话的结束时间
        userMap.remove(session.getId());
        connectedUser.remove(session.getId());

    }

    @OnError
    public void onError(Throwable t) {

    }

}
