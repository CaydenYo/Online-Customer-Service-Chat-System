package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.MVSChatLogBean;
import com.pentaKill.domain.MVSConversationBean;

public interface ManagerViewsSessionMapper {
    public List<MVSConversationBean> getCurrentConversationMapper();

    public MVSConversationBean getCurrentConversationByIdMapper(int conversation_id);

    public List<MVSChatLogBean> getChatLogMapper(int conversation_id);

    public List<MVSConversationBean> getHistoryConversationMapper();
}
