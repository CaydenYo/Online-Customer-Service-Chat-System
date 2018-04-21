package com.pentaKill.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.ChatLogBean;
import com.pentaKill.domain.ChooseCustomerServiceBean;
import com.pentaKill.domain.ConversationBean;
import com.pentaKill.domain.FindConversationBean;
import com.pentaKill.domain.LastCustomerServiceBean;


public class ConversationServiceTest extends BaseTest{

    
    @Autowired
    private ConversationService cs;
    
    @Test
    public void testFindConversationIdService() {
        //fail("Not yet implemented");
        int customer_id = 2000;
        int cs_id = 1000;
        FindConversationBean fcb = new FindConversationBean(customer_id,cs_id);
        int c_id = cs.findConversationIdService(fcb);
        Assert.assertEquals("Conversation查询出错", 1, c_id);
    }
    
    @Test
    public void testInsertConversationService() {
        Timestamp t = new Timestamp(System.currentTimeMillis()) ;
        ConversationBean cb = new ConversationBean(2000, 1001,t, t, 2);
        cs.insertConversationService(cb);
        FindConversationBean fcb = new FindConversationBean(2000,1001);
        int c_id = cs.findConversationIdService(fcb);
        System.out.println(c_id);
    }

    @Test
    public void testInsertChatLogService() {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        ChatLogBean clb = new ChatLogBean(1, 2000, 1000, 1, t,0, "aaa");
        cs.insertChatLogService(clb);
    }

   
    @Test
    public void testSelectCustomerServiceByStatus() {
        List<ChooseCustomerServiceBean> list = new LinkedList<ChooseCustomerServiceBean>();
        list = cs.selectCustomerServiceByStatus();
        System.out.println(list);
    }

    @Test
    public void testSelectLastCustomerService() {
        List<LastCustomerServiceBean> list = cs.selectLastCustomerService(2000);
        System.out.println("------");
        System.out.println(list);
    }

    @Test
    public void testSelecLasttCustomerServiceInfo() {
        ChooseCustomerServiceBean ccsb = cs.selecLasttCustomerServiceInfo(2000);
        System.out.println(ccsb);
    }

    @Test
    public void testGetDistributionType() {
        String name = "facebook";
        int a = cs.getDistributionType(name);
        Assert.assertEquals("查询DistributionType出错", 0, a);
    }

    @Test
    public void testInserCustomerWaitingTeam() {
        int customerId = 2000;
        int csId = 1000;
        cs.inserCustomerWaitingTeam(2000, 1000);
    }

    @Test
    public void testIncreaseCsManageToolWaitingPeople() {
        cs.increaseCsManageToolWaitingPeople(1);
    }

    @Test
    public void testDeleteCustomerWaitingTeam() {
        cs.deleteCustomerWaitingTeam(1000, 2001);
    }

    @Test
    public void testDecreaseCsManageToolWaitingPeople() {
        cs.decreaseCsManageToolWaitingPeople(1);
    }

    @Test
    public void testGetCsNicknameByCsId() {
        String s = cs.getCsNicknameByCsId(1000);
        Assert.assertEquals("查询Nickname出错", "kefu1", s);
    }

    @Test
    public void testGetCustomerNicknameByCustomerId() {
        String string = cs.getCustomerNicknameByCustomerId(2000);
        Assert.assertEquals("查询CustomerNicknamed出错", "yukang", string);
    }

}
