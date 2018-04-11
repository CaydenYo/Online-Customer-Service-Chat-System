package com.pentaKill.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pentaKill.domain.MVSChatLogBean;
import com.pentaKill.domain.MVSConversationBean;
import com.pentaKill.service.CustomerServiceService;
import com.pentaKill.service.ManagerViewsSessionService;

import net.sf.json.JSONObject;

@Controller
public class ManagerViewsSessionController {
    @Resource
    ManagerViewsSessionService managerViewsSessionService;
    @Resource
    HttpServletRequest req;

    // 查看当前会话
    @RequestMapping(value = "/managerViewsCurrentConversation.action")
    @ResponseBody
    public String managerViewsCurrentConversation() {
        List<MVSConversationBean> mvscbList = managerViewsSessionService.getCurrentConversationService();
        Gson gson = new Gson();
        return gson.toJson(mvscbList);
    }

    // 查看详细的聊天记录
    @RequestMapping(value = "/managerViewsChatLog.action")
    @ResponseBody
    public String managerViewsChatLog() {
        String data = req.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        // 传入以下信息
        int conversation_id = Integer.parseInt(json.getString("conversation_id"));
        List<MVSChatLogBean> mvsChatLogList = managerViewsSessionService.getChatLogService(conversation_id);
        Gson gson = new Gson();
        return gson.toJson(mvsChatLogList);

    }

    // 查看历史会话信息
    @RequestMapping(value = "/managerViewsHistoryConversation.action")
    @ResponseBody
    public String managerViewsHistoryConversation() {
        List<MVSConversationBean> mvscbList = managerViewsSessionService.getHistoryConversationService();
        Gson gson = new Gson();
        return gson.toJson(mvscbList);

    }
}
