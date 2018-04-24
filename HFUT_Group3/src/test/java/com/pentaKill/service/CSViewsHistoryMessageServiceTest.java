/**
 * Hyc
 *2018年4月21日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.NewChatLogBean;

/**
 * @author Hyc
 *
 */
public class CSViewsHistoryMessageServiceTest extends BaseTest{

    
    @Autowired
    private CSViewsHistoryMessageService cchms;

    /**
     * Test method for {@link com.pentaKill.service.CSViewsHistoryMessageService#historyMessageFlagService(int)}.
     */
    @Test
    public void testHistoryMessageFlagService() {
        boolean flag1 = cchms.historyMessageFlagService(2000);
        boolean flag2 = cchms.historyMessageFlagService(6000);
        
        Assert.assertEquals("historyMessage出错", true, flag1);
        Assert.assertEquals("historyMessage出错", false, flag2);
        
    }

    /**
     * Test method for {@link com.pentaKill.service.CSViewsHistoryMessageService#getChatlogService(int, int)}.
     */
    @Test
    public void testGetChatlogService() {
        List<NewChatLogBean> list = cchms.getChatlogService(2000,1000);
        for(NewChatLogBean n:list){
            System.out.println(n);
        }
    }

}
