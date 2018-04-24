/**
 * Hyc
 *2018年4月21日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.MVSChatLogBean;
import com.pentaKill.domain.MVSConversationBean;

/**
 * @author Hyc
 *
 */
public class ManagerViewsSessionServiceTest extends BaseTest{
    
    @Resource
    private ManagerViewsSessionService mss;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link com.pentaKill.service.ManagerViewsSessionService#getCurrentConversationService()}.
     */
    @Test
    public void testGetCurrentConversationService() {
        List<MVSConversationBean> list = mss.getCurrentConversationService();
        for(MVSConversationBean mvscb : list){
            System.out.println(mvscb);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.ManagerViewsSessionService#getChatLogService(int)}.
     */
    @Test
    public void testGetChatLogService() {
        int conversation_id = 1;
        List<MVSChatLogBean> list = mss.getChatLogService(conversation_id);
        for(MVSChatLogBean mvsclb:list){
            System.out.println(mvsclb);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.ManagerViewsSessionService#getHistoryConversationService()}.
     */
    @Test
    public void testGetHistoryConversationService() {
        List<MVSConversationBean> list = mss.getHistoryConversationService();
        for(MVSConversationBean mvscb:list){
            System.out.println(mvscb);
        }
    }

}
