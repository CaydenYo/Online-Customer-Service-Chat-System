/**
 * Hyc
 *2018年4月18日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.CsEvaluateBean;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceLoginBean;
import com.pentaKill.domain.CustomerServiceRegisterBean;
import com.pentaKill.domain.CustomerServiceStatusBean;
import com.pentaKill.domain.ListCSInfoBean;
import com.pentaKill.domain.WaitingQueueCustomerInfo;
import com.pentaKill.exception.LoginException;

/**
 * @author Hyc
 *
 */
public class CustomerServiceServiceTest extends BaseTest{

    @Autowired
    private CustomerServiceService css;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#csLogin(com.pentaKill.domain.CustomerServiceLoginBean)}.
     * @throws LoginException 
     */
    @Test
    public void testCsLogin() throws LoginException {
        CustomerServiceLoginBean cslb1 = new CustomerServiceLoginBean("841160480@qq.com","123456");
        CustomerServiceLoginBean cslb2 = new CustomerServiceLoginBean("333@qq.com","123456");
        CustomerService cs1 = css.csLogin(cslb1);
        CustomerService cs2 = css.csLogin(cslb2);
        Assert.assertEquals("登录cs出错", "841160480@qq.com", cs1.getCs_email());
        Assert.assertEquals("登录cs出错", null, cs2);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#listCustomerSStatus(int)}.
     */
    @Test
    public void testListCustomerSStatus() {
        int company_id =1;
        List<CustomerServiceStatusBean> list = css.listCustomerSStatus(company_id);
        for(CustomerServiceStatusBean cssb:list){
            System.out.println(cssb);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#listTotalOperatingNum(int)}.
     */
    @Test
    public void testListTotalOperatingNum() {
        int company_id =1;
        int sum = css.listTotalOperatingNum(company_id);
        Assert.assertEquals("ListTotalOperatingNum出错", 6, sum);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#listTotalWaitingNum(int)}.
     */
    @Test
    public void testListTotalWaitingNum() {
        int company_id =1;
        int sum = css.listTotalWaitingNum(company_id);
        Assert.assertEquals("ListTotalWaitingNum出错", 5, sum);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#reg(com.pentaKill.domain.CustomerServiceRegisterBean)}.
     */
    @Test
    public void testReg() {
        CustomerServiceRegisterBean customerServiceRegisterBean = new CustomerServiceRegisterBean();
        customerServiceRegisterBean.setCs_email("6666@qq.com");
        customerServiceRegisterBean.setCs_workId("2222");
        boolean flag = css.reg(customerServiceRegisterBean);
        Assert.assertEquals("ListTotalWaitingNum出错", true, flag);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#regconf(java.lang.String)}.
     */
    @Test
    public void testRegconf() {
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#selectByEmail(java.lang.String)}.
     */
    @Test
    public void testSelectByEmail() {
        CustomerService cs = css.selectByEmail("841160480@qq.com");
        Assert.assertEquals("SelectByEmail出错", 1001, cs.getCs_id());
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#setStatus(int, com.pentaKill.domain.CustomerService)}.
     */
    @Test
    public void testSetStatus() {
        CustomerService customerService = css.selectByEmail("841160480@qq.com");
        css.setStatus(0, customerService);
        CustomerService customerService2 = css.selectByEmail("841160480@qq.com");
        Assert.assertEquals("SelectByEmail出错", 0, customerService2.getCs_status());
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#setNumber(int, int, com.pentaKill.domain.CustomerService)}.
     */
    @Test
    public void testSetNumber() {
        CustomerService customerService = css.selectByEmail("841160480@qq.com");
        css.setNumber(5,5, customerService);
        CustomerService customerService2 = css.selectByEmail("841160480@qq.com");
        Assert.assertEquals("Setoperating_Number出错", 5, customerService2.getCs_operating_number());
        Assert.assertEquals("Setwaiting_Number出错", 5, customerService2.getCs_waiting_number());
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#setProfile(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pentaKill.domain.CustomerService)}.
     */
    @Test
    public void testSetProfile() {
        CustomerService customerService = css.selectByEmail("841160480@qq.com");
        css.setImg("aaa.jpg", customerService);
        css.setPwd("111", customerService);
        css.setName("haha", customerService);
        CustomerService customerService2 = css.selectByEmail("841160480@qq.com");
        Assert.assertEquals("SetPwd出错", "111", customerService2.getCs_pwd());
        Assert.assertEquals("SetImg出错", "aaa.jpg", customerService2.getCs_img());
        Assert.assertEquals("SetNickname出错", "haha", customerService2.getCs_nickName());
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#insertNewEvaluateService(com.pentaKill.domain.CsEvaluateBean)}.
     */
    @Test
    public void testInsertNewEvaluateService() {
        CsEvaluateBean csb = new CsEvaluateBean();
        csb.setContent("aaa");
        csb.setCs_id(1000);
        csb.setCs_score(5);
        Timestamp t = new Timestamp(System.currentTimeMillis()) ;
        csb.setEvaluate_time(t);
        css.insertNewEvaluateService(csb);
        
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getCSInfoList(int)}.
     */
    @Test
    public void testGetCSInfoList() {
        int company_id = 1;
        List<ListCSInfoBean> list = css.getCSInfoList( company_id);
        for(ListCSInfoBean lcsb:list){
            System.out.println(lcsb);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#countServiceNum(int)}.
     */
    @Test
    public void testCountServiceNum() {
        int company_id = 1 ;
        int num = css.countServiceNum(company_id);
        Assert.assertEquals("CountServiceNum出错", 3, num);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#countOnlineServiceNum(int)}.
     */
    @Test
    public void testCountOnlineServiceNum() {
        int company_id = 1 ;
        int num = css.countOnlineServiceNum(company_id);
        Assert.assertEquals("CountOnlineServiceNum出错", 2, num);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getCountToday(int)}.
     */
    @Test
    public void testGetCountToday() {
        int company_id = 1 ;
        int num = css.getCountToday(company_id);
        Assert.assertEquals("getCountToday出错", 0, num);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getCount(int)}.
     */
    @Test
    public void testGetCount() {
        int company_id = 1 ;
        int num = css.getCount(company_id);
        Assert.assertEquals("getCount出错", 0, num);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getScore(int)}.
     */
    @Test
    public void testGetScore() {
        int cs_id = 1000 ;
        int num = css.getScore(cs_id);
        Assert.assertEquals("getScore出错", 15, num);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getTime(int)}.
     */
    @Test
    public void testGetTime() {
        int cs_id = 1000 ;
        int num = css.getTime(cs_id);
        Assert.assertEquals("getScore出错", 0, num);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getWaitingQueue(int)}.
     */
    @Test
    public void testGetWaitingQueue() {
        int cs_id = 1000;
        List<Integer> list = css.getWaitingQueue(cs_id);
        for(Integer i:list){
            System.out.println(i);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getCustomerInfo(int)}.
     */
    @Test
    public void testGetCustomerInfo() {
        int customer_id = 2000;
        WaitingQueueCustomerInfo info = css.getCustomerInfo(customer_id);
        Assert.assertEquals("getWaitingQueue出错", 2000, info.getCustomer_id());
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#deleteWaitingCustomer(int)}.
     */
    @Test
    public void testDeleteWaitingCustomer() {
        int customer_id = 2000;
        css. deleteWaitingCustomer(customer_id);
        List<Integer> list = css.getWaitingQueue(customer_id);
        Assert.assertEquals("getWaitingQueue出错", true, list.isEmpty());
    }

}
