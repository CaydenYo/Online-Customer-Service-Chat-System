/**
 * Hyc
 *2018年4月12日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.CustomerInfoBean;

/**
 * @author Hyc
 *
 */
public class CustomerLoginServiceTest extends BaseTest{

    @Autowired
    private CustomerLoginService cls;
    /**
     * Test method for {@link com.pentaKill.service.CustomerLoginService#customerLoginService(java.lang.String)}.
     */
    @Test
    public void testCustomerLoginService() {
        CustomerInfoBean cib = cls.customerLoginService("yukang");
        Assert.assertEquals("查询Customer出错", "123", cib.getCustomer_pwd());
    }

}
