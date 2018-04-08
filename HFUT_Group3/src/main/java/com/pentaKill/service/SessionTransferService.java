package com.pentaKill.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.SessionTransferMapper;
import com.pentaKill.domain.ConversationBean;

@Service
@Scope
public class SessionTransferService {
    @Resource
    SessionTransferMapper sessionTransferMapper;

    public void decreaseCsWaitingNumService(int cs_id) {
        sessionTransferMapper.decreaseCsWaitingNumMapper(cs_id);
    }

    public void addCsWaitingNumService(int cs_id) {
        sessionTransferMapper.addCsWaitingNumMapper(cs_id);
    }

    public void closeConversationService(ConversationBean cb) {
        sessionTransferMapper.closeConversationMapper(cb);
    }
}
