package com.pentaKill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pentaKill.domain.CSViewsCustomerInfoBean;
import com.pentaKill.service.CSViewsHistoryMessageService;

import net.sf.json.JSONObject;

@Controller
public class CSViewsHistoryMessageController {
    @Resource
    CSViewsHistoryMessageService csViewsHistoryMessageService;
    @Resource
    HttpServletRequest req;

    // 需求不明确，如果当前可一个顾客聊天，切换到另一个页面，此时是请求数据库加载数据吗？还是本地缓存呢？
    // 那么需要加载多少数据呢，比如加载十条？之前的消息记录也需要再点一次查看聊天记录吗？

    // 先要判断是否有历史会话信息，即判断是否是老顾客
    // 根据结果选择是否显示查看历史消息按钮
    @RequestMapping(value = "/historyMessageFlag.action", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String historyMessageFlag() {
        // 查询会话数据库，判断是否有历史会话
        String data = req.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        // 传入顾客的id
        int customer_id = Integer.parseInt(json.getString("customer_id"));
        boolean flag = csViewsHistoryMessageService.historyMessageFlagService(customer_id);

        Gson gson = new Gson();
        if (flag) {
            return gson.toJson("success");
        } else {
            return gson.toJson("fail");
        }

    }

    // 选择一个客户，加载这个用户与之前所有客服的历史会话
    // 包括头像和昵称

}
