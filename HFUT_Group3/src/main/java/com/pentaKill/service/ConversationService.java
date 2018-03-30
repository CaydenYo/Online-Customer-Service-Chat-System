package com.pentaKill.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.ConversationMapper;
import com.pentaKill.domain.ChatLogBean;
import com.pentaKill.domain.ConversationBean;
import com.pentaKill.domain.FindConversationBean;

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
}
