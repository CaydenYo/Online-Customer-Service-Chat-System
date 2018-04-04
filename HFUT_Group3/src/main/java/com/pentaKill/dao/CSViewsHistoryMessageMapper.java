package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.CSCLBean;
import com.pentaKill.domain.CustomerCLBean;
import com.pentaKill.domain.GetCoversationIdBean;
import com.pentaKill.domain.NewChatLogBean;

public interface CSViewsHistoryMessageMapper {
    public List<GetCoversationIdBean> getCLConversationId_mapper(int customer_id);

    public CustomerCLBean getCustomerCLInfo_mapper(int customer_id);

    public CSCLBean getCSCLInfo_mapper(int cs_id);

    public List<NewChatLogBean> getChatLog_mapper(int conversation_id);
}
