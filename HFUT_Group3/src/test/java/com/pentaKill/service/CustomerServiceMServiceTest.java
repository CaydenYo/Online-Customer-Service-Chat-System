/**
 * Hyc
 *2018年4月13日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.CSManager;
import com.pentaKill.domain.CSManagerLoginBean;
import com.pentaKill.exception.LoginException;
import com.pentaKill.exception.RegisterException;

/**
 * @author Hyc
 *
 */
public class CustomerServiceMServiceTest extends BaseTest{

    @Autowired
    private CustomerServiceMService csms;

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceMService#csmLogin(com.pentaKill.domain.CSManagerLoginBean)}.
     * @throws LoginException 
     */
    @Test
    public void testCsmLogin() throws LoginException {
        CSManagerLoginBean csManagerLoginBean1 = new CSManagerLoginBean("88888888@qq.com", "123456");
        CSManagerLoginBean csManagerLoginBean2 = new CSManagerLoginBean("833333@qq.com", "222");
        CSManager csm1 = csms.csmLogin(csManagerLoginBean1);
        CSManager csm2 = csms.csmLogin(csManagerLoginBean2);
        System.out.println(csm1);
        Assert.assertEquals("登录CSManager出错", 1, csm1.getCompany_id());
        Assert.assertEquals("登录CSManager出错", "88888888@qq.com", csm1.getCsm_email());
        Assert.assertEquals("登录CSManager出错", "123456", csm1.getCsm_pwd());
        Assert.assertEquals("登录CSManager出错", null, csm2);
    }

    /**
     * Test method for {@link com.pentaKill.service.CustomerServiceMService#csmRegister(com.pentaKill.domain.CSManager)}.
     * @throws RegisterException 
     * @throws LoginException 
     */
    @Test
    public void testCsmRegister() throws RegisterException, LoginException {
        CSManager csm = new CSManager();
        csm.setCompany_id(1);
        csm.setCsm_email("6666@qq.com");
        csm.setCsm_pwd("1111");
        csms.csmRegister(csm);
        CSManagerLoginBean csManagerLoginBean1 = new CSManagerLoginBean("6666@qq.com", "1111");
        CSManager csm1 = csms.csmLogin(csManagerLoginBean1);
        Assert.assertEquals("注册CSManager出错", "6666@qq.com", csm1.getCsm_email());
    }

}
