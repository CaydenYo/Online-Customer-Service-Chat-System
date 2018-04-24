/**
 * Hyc
 *2018年4月23日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.WaitingCustomerBean;

/**
 * @author Hyc
 *
 */
public class CustomerWaitingServiceTest extends BaseTest{

    @Autowired
    private CustomerWaitingService cws;

    /**
     * Test method for {@link com.pentaKill.service.CustomerWaitingService#selectCustomerByCsId(int)}.
     */
    @Test
    public void testSelectCustomerByCsId() {
        List<WaitingCustomerBean> list = cws.selectCustomerByCsId(1000);
        Assert.assertEquals("得到Customer失败",false, list.isEmpty());
    }

}
