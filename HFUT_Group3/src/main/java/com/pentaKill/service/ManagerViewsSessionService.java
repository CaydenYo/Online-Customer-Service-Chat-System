package com.pentaKill.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.ManagerViewsSessionMapper;
import com.pentaKill.domain.MVSChatLogBean;
import com.pentaKill.domain.MVSConversationBean;

@Service
@Scope
public class ManagerViewsSessionService {
    @Resource
    ManagerViewsSessionMapper managerViewsSessionMapper;

    public List<MVSConversationBean> getCurrentConversationService() {
        List<MVSConversationBean> temp = managerViewsSessionMapper.getCurrentConversationMapper();
        return temp;
    }

    public List<MVSChatLogBean> getChatLogService(int conversation_id) {
        List<MVSChatLogBean> temp = managerViewsSessionMapper.getChatLogMapper(conversation_id);
        MVSConversationBean mvscb = managerViewsSessionMapper.getCurrentConversationByIdMapper(conversation_id);
        String customer_nickname = mvscb.getCustomer_nickname();
        String cs_nickname = mvscb.getCs_nickName();
        for (int i = 0; i < temp.size(); i++) {
            MVSChatLogBean chatbean = temp.get(i);

            // 把客服昵称和客户昵称填入聊天记录
            if (chatbean.getFrom_customer() == 0) {
                // 来自客户
                chatbean.setReceiver_nickname(cs_nickname);
                chatbean.setSender_nickname(customer_nickname);
            } else {
                chatbean.setReceiver_nickname(customer_nickname);
                chatbean.setSender_nickname(cs_nickname);
            }
        }
        return temp;
    }

    public List<MVSConversationBean> getHistoryConversationService() {
        List<MVSConversationBean> temp = managerViewsSessionMapper.getHistoryConversationMapper();
        return temp;
    }
}
