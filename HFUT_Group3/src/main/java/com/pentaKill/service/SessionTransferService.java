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

    public void decreaseCsOperatedNumService(int cs_id) {
        sessionTransferMapper.decreaseCsOperatedNumMapper(cs_id);
    }

    public void addCsOperatedNumService(int cs_id) {
        sessionTransferMapper.addCsOperatedNumMapper(cs_id);
    }

    public void closeConversationService(ConversationBean cb) {
        sessionTransferMapper.closeConversationMapper(cb);
    }
    
    public void addCsWaitedNumService(int cs_id){
        sessionTransferMapper.addCsWaitedNumService(cs_id);
    }
    
    public void decreaseCsWaitedNumService(int cs_id){
        sessionTransferMapper.decreaseCsWaitedNumService(cs_id);
    }
}
