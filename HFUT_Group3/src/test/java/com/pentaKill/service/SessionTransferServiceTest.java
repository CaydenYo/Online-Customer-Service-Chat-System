/**
 * Hyc
 *2018年4月20日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.ConversationBean;

/**
 * @author Hyc
 *
 */
public class SessionTransferServiceTest extends BaseTest{

    @Autowired
    private SessionTransferService stfs;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link com.pentaKill.service.SessionTransferService#decreaseCsOperatedNumService(int)}.
     */
    @Test
    public void testDecreaseCsOperatedNumService() {
        stfs.decreaseCsOperatedNumService(1000);
        
    }

    /**
     * Test method for {@link com.pentaKill.service.SessionTransferService#addCsOperatedNumService(int)}.
     */
    @Test
    public void testAddCsOperatedNumService() {
        stfs.addCsOperatedNumService(1000);
    }

    /**
     * Test method for {@link com.pentaKill.service.SessionTransferService#closeConversationService(com.pentaKill.domain.ConversationBean)}.
     */
    @Test
    public void testCloseConversationService() {
        Timestamp t = new Timestamp(System.currentTimeMillis()) ;
        ConversationBean cb = new ConversationBean(2000, 1001,t, t, 2);
        stfs.closeConversationService(cb);
    }

}
