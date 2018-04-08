package com.pentaKill.dao;

import com.pentaKill.domain.ConversationBean;

public interface SessionTransferMapper {
    public void decreaseCsWaitingNumMapper(int cs_id);

    public void addCsWaitingNumMapper(int cs_id);

    public void closeConversationMapper(ConversationBean cb);

}
