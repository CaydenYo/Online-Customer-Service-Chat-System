package com.pentaKill.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.ConversationMapper;
import com.pentaKill.domain.ChatLogBean;
import com.pentaKill.domain.ChooseCustomerServiceBean;
import com.pentaKill.domain.ConversationBean;
import com.pentaKill.domain.FindConversationBean;
import com.pentaKill.domain.LastCustomerServiceBean;

@Service
@Scope
public class ConversationService {
	@Resource
	ConversationMapper conversationMapper;
	
	public void insertConversation_service(ConversationBean cb){
		conversationMapper.startConversation_mapper(cb);
	}
	
	public void insertChatLog_service(ChatLogBean clb){
		conversationMapper.insertChatLog_mapper(clb);
	}
	
	public int findConversationId(FindConversationBean fcb){
		int conversation_id=conversationMapper.findConversationId_mapper(fcb);
		return conversation_id;
	}
	
    public List<ChooseCustomerServiceBean> selectCustomerServiceByStatus(){
        List<ChooseCustomerServiceBean> list = new LinkedList<ChooseCustomerServiceBean>();
        list = conversationMapper.selectCustomerServiceByStatus();
        return list;
    }
    
    public List<LastCustomerServiceBean> selectLastCustomerService(int customer_id){
        List<LastCustomerServiceBean> list = new LinkedList<LastCustomerServiceBean>();
        list = conversationMapper.selectLastCustomer(customer_id);
        return list;
    }
    
    public ChooseCustomerServiceBean selecLasttCustomerServiceInfo(int cs_id){
        ChooseCustomerServiceBean ccs = new ChooseCustomerServiceBean();
        ccs = conversationMapper.selectLastCustomerServiceBean(cs_id);
        return ccs;
    }
    
    public int getDistributionType(String company_name){
        int distributionType;
        distributionType = conversationMapper.getDistributionType(company_name);
        return distributionType;
    }
     
    public void inserCustomerWaitingTeam(int customerId, int csId){
        conversationMapper.insertWaitingCustomer(customerId, csId);
    }
    
    public void increaseCsManageToolWaitingPeople(){
        conversationMapper.increaseCsManageToolWaitingPeople();
    }
    
    public void deleteCustomerWaitingTeam(int csId, int customerId){
        conversationMapper.deleteCustomerWaitingTeam(csId, customerId);
    }
    
    public void decreaseCsManageToolWaitingPeople(){
        conversationMapper.decreaseCsManageToolWaitingPeople();
    }
    
    public String getCsNicknameByCsId(int cs_id){
        return conversationMapper.getCsNicknameByCsId(cs_id);
    }
    
    public String getCustomerNicknameByCustomerId(int customer_id){
        return conversationMapper.getCustomerNicknameByCustomerId(customer_id);
    }
}
