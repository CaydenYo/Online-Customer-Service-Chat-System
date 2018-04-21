/**
 * Hyc
 *2018年4月19日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.CSViewsCustomerInfoBean;
import com.pentaKill.domain.CustomerInfoBean;


/**
 * @author Hyc
 *
 */


public class CSViewsCustomerInfoServiceTest extends BaseTest{

    @Autowired
    private CSViewsCustomerInfoService css;

    /**
     * Test method for {@link com.pentaKill.service.CSViewsCustomerInfoService#searchCustomerInfoFlagService(int)}.
     */
    @Test
    public void testSearchCustomerInfoFlagService() {
        int flag = css.searchCustomerInfoFlagService(1);
        Assert.assertEquals("CustomerInfoFlag查询出错", 0, flag);
    }

    /**
     * Test method for {@link com.pentaKill.service.CSViewsCustomerInfoService#searchCompanyIdService(int)}.
     */
    @Test
    public void testSearchCompanyIdService() {
        int c_id = css.searchCompanyIdService(1000);
        Assert.assertEquals("CompanyId查询出错", 1, c_id);
    }

    /**
     * Test method for {@link com.pentaKill.service.CSViewsCustomerInfoService#searchCustomerInfoService(int)}.
     */
    
    @Test
    public void testSearchCustomerInfoService() {
        CustomerInfoBean cib = css.searchCustomerInfoService(2000);
        Assert.assertEquals("CustomerInfo查询出错", "yukang", cib.getCustomer_name());
    }

    /**
     * Test method for {@link com.pentaKill.service.CSViewsCustomerInfoService#searchCurrentTime(int)}.
     */
    @Test
    public void testSearchCurrentTime() {
        List<CSViewsCustomerInfoBean> list = css.searchCurrentTime(1000);
        System.out.println(list);
    }

}
