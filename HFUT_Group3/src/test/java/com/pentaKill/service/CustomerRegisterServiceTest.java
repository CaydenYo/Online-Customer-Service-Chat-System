/**
 * Hyc
 *2018年4月12日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.CustomerRegisterBean;

/**
 * @author Hyc
 *
 */
public class CustomerRegisterServiceTest extends BaseTest{

    @Autowired
    private CustomerRegisterService crs;
    @Autowired
    private CustomerLoginService cls;


    /**
     * Test method for {@link com.pentaKill.service.CustomerRegisterService#customerRegisterService(com.pentaKill.domain.CustomerRegisterBean)}.
     */
    @Test
    public void testCustomerRegisterService() {
        CustomerRegisterBean crb = new CustomerRegisterBean("aaa", 1, "8888@qq.com", "aaa111","南京");
        crs.customerRegisterService(crb);
        cls.customerLoginService("aaa");
    }

}
