package com.pentaKill.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CSViewsCustomerInfoMapper;
import com.pentaKill.dao.CSViewsHistoryMessageMapper;
import com.pentaKill.domain.CSCLBean;
import com.pentaKill.domain.CustomerCLBean;
import com.pentaKill.domain.GetCoversationIdBean;
import com.pentaKill.domain.NewChatLogBean;

@Service
@Scope
public class CSViewsHistoryMessageService {
    @Resource
    CSViewsHistoryMessageMapper csViewsHistoryMessageMapper;

    public boolean historyMessageFlagService(int customer_id) {
        List<GetCoversationIdBean> temp = csViewsHistoryMessageMapper.getCLConversationIdMapper(customer_id);
        if (temp.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public List<NewChatLogBean> getChatlogService(int customer_id, int cs_id) {
        List<NewChatLogBean> ans = new ArrayList<NewChatLogBean>();
        List<GetCoversationIdBean> conversation_list = csViewsHistoryMessageMapper
                .getCLConversationIdMapper(customer_id);
        for (int i = 0; i < conversation_list.size(); i++) {
            GetCoversationIdBean gcib = conversation_list.get(i);
            String customer_nickname = null;
            String customer_img = null;
            String cs_nickName = null;
            String cs_img = null;
            // 查找这一段会话中，客服与客户的头像昵称
            CustomerCLBean cclb = csViewsHistoryMessageMapper.getCustomerCLInfoMapper(gcib.getCustomer_id());
            CSCLBean csclb = csViewsHistoryMessageMapper.getCSCLInfoMapper(gcib.getCs_id());

            customer_nickname = cclb.getCustomer_nickname();
            customer_img = cclb.getCustomer_img();
            cs_nickName = csclb.getCs_nickName();
            cs_img = csclb.getCs_img();

            // 把这些信息写入相关的聊天记录中
            int conversation_id = conversation_list.get(i).getConversation_id();
            List<NewChatLogBean> temp = csViewsHistoryMessageMapper.getChatLogMapper(conversation_id);

            for (int j = 0; j < temp.size(); j++) {
                // 将发送者的昵称和头像写入其中
                // 小心下标j出错
                if (temp.get(j).getSender_id() < 2000) {
                    // 是客服
                    temp.get(j).setSender_nickname(cs_nickName);
                    temp.get(j).setSender_img(cs_img);
                } else {
                    // 是用户
                    temp.get(j).setSender_nickname(customer_nickname);
                    temp.get(j).setSender_img(customer_img);
                }
                ans.add(temp.get(j));
            }
        }
        ans.sort(null);
        return ans;

    }

}
