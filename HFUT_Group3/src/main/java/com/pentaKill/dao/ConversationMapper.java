package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.ChatLogBean;
import com.pentaKill.domain.ChooseCustomerServiceBean;
import com.pentaKill.domain.ConversationBean;
import com.pentaKill.domain.FindConversationBean;
import com.pentaKill.domain.LastCustomerServiceBean;

public interface ConversationMapper {
    public void startConversation_mapper(ConversationBean cb);

    public void insertChatLog_mapper(ChatLogBean clb);

    public int findConversationId_mapper(FindConversationBean fcb);

    public List<ChooseCustomerServiceBean> selectCustomerServiceByStatus();
    
    public List<LastCustomerServiceBean> selectLastCustomer(int customer_id);
    
    public ChooseCustomerServiceBean selectLastCustomerServiceBean(int cs_id);
    
    public int getDistributionType(String company_name);
    
    public void insertWaitingCustomer(int customerId, int csId);
    
    public void increaseCsManageToolWaitingPeople();
    
    public void deleteCustomerWaitingTeam(int csId, int customerId);
    
    public void decreaseCsManageToolWaitingPeople();
    
    public String getCsNicknameByCsId(int cs_id);
    
    public String getCustomerNicknameByCustomerId(int customer_id);
}
