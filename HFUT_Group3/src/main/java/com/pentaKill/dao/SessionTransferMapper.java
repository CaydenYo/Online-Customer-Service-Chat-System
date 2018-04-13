package com.pentaKill.dao;

import com.pentaKill.domain.ConversationBean;

public interface SessionTransferMapper {
    public void decreaseCsOperatedNumMapper(int cs_id);

    public void addCsOperatedNumMapper(int cs_id);

    public void closeConversationMapper(ConversationBean cb);

}
