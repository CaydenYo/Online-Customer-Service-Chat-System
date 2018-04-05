package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.CSCLBean;
import com.pentaKill.domain.CustomerCLBean;
import com.pentaKill.domain.GetCoversationIdBean;
import com.pentaKill.domain.NewChatLogBean;

public interface CSViewsHistoryMessageMapper {
    public List<GetCoversationIdBean> getCLConversationIdMapper(int customer_id);

    public CustomerCLBean getCustomerCLInfoMapper(int customer_id);

    public CSCLBean getCSCLInfoMapper(int cs_id);

    public List<NewChatLogBean> getChatLogMapper(int conversation_id);
}
