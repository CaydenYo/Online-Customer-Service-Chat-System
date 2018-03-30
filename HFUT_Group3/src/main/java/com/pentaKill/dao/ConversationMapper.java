package com.pentaKill.dao;

import com.pentaKill.domain.ChatLogBean;
import com.pentaKill.domain.ConversationBean;
import com.pentaKill.domain.FindConversationBean;

public interface ConversationMapper {
	public void startConversation_mapper(ConversationBean cb);
	
	public void insertChatLog_mapper(ChatLogBean clb);
	
	public int findConversationId_mapper(FindConversationBean fcb);
}
