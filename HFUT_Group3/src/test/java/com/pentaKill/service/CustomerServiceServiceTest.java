/**
 * Hyc
 *2018年4月18日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceLoginBean;
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
        CustomerServiceLoginBean cslb = new CustomerServiceLoginBean("841160480@qq.com","123456");
        CustomerService cs = css.csLogin(cslb);
        Assert.assertEquals("登录cs出错", "841160480@qq.com", cs.getCs_email());
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#listCustomerSStatus(int)}.
     */
    @Test
    public void testListCustomerSStatus() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#listTotalOperatingNum(int)}.
     */
    @Test
    public void testListTotalOperatingNum() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#listTotalWaitingNum(int)}.
     */
    @Test
    public void testListTotalWaitingNum() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#reg(com.pentaKill.domain.CustomerServiceRegisterBean)}.
     */
    @Test
    public void testReg() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#regconf(java.lang.String)}.
     */
    @Test
    public void testRegconf() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#selectByEmail(java.lang.String)}.
     */
    @Test
    public void testSelectByEmail() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#setStatus(int, com.pentaKill.domain.CustomerService)}.
     */
    @Test
    public void testSetStatus() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#setNumber(int, int, com.pentaKill.domain.CustomerService)}.
     */
    @Test
    public void testSetNumber() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#setProfile(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pentaKill.domain.CustomerService)}.
     */
    @Test
    public void testSetProfile() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#insertNewEvaluateService(com.pentaKill.domain.CsEvaluateBean)}.
     */
    @Test
    public void testInsertNewEvaluateService() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getCSInfoList(int)}.
     */
    @Test
    public void testGetCSInfoList() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#countServiceNum(int)}.
     */
    @Test
    public void testCountServiceNum() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#countOnlineServiceNum(int)}.
     */
    @Test
    public void testCountOnlineServiceNum() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getCountToday(int)}.
     */
    @Test
    public void testGetCountToday() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getCount(int)}.
     */
    @Test
    public void testGetCount() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getScore(int)}.
     */
    @Test
    public void testGetScore() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getTime(int)}.
     */
    @Test
    public void testGetTime() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getWaitingQueue(int)}.
     */
    @Test
    public void testGetWaitingQueue() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#getCustomerInfo(int)}.
     */
    @Test
    public void testGetCustomerInfo() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceService#deleteWaitingCustomer(int)}.
     */
    @Test
    public void testDeleteWaitingCustomer() {
        fail("Not yet implemented");
    }

}
