package com.pentaKill.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.pentaKill.BaseTest;
import com.pentaKill.dao.CompanyMapper;
import com.pentaKill.dao.CustomerServiceMapper;
import com.pentaKill.domain.Company;
import com.pentaKill.domain.CustomerInfoBean;
import com.pentaKill.domain.CustomerService;


/**
 * Hyc
 *2018年4月13日
 */
public class CompanyServiceTest extends BaseTest {

    @Autowired
    private CompanyService cs;

    @Autowired
    private CompanyMapper cm;

    @Test
    public void testFindCompany() {

        // insert into companyInfo values(1,"facebook",0,0,1,0,0,0,"miki");
        // insert into companyInfo values(2,"CISCO",0,0,0,0,0,0,"XIAO AI");
        // insert into companyInfo values(3,"SUMSUNG",1,1,1,1,3,1,"XIAO BING");
        Company c1 = cs.findCompany(1);
        Company c2 = cs.findCompany(2);
        Assert.assertEquals("公司查询出错", "facebook", c1.getCompany_name());
        Assert.assertEquals("公司查询出错", "CISCO", c2.getCompany_name());

    }

    @Test
    public void testUpdateAccess() {
        int min_num = 2;
        Company c1 = cs.findCompany(1);
        cs.updateAccess(c1, min_num);
        Assert.assertEquals("更新Access失败", 2, cs.findCompany(1).getAccess_type());

    }

    @Test
    public void testUpdate() {
        int dstribution_type = 1;
        int customer_info_flag = 1;
        int min_num = 1;
        Company c1 = cs.findCompany(1);
        cs.update(c1, dstribution_type, customer_info_flag, min_num);
        cs.updateAccess(c1, min_num);
        Assert.assertEquals("更新dstribution_type失败", 1, cs.findCompany(1).getDstribution_type());
        Assert.assertEquals("更新customer_info_flag失败", 1, cs.findCompany(1).getCustomer_info_flag());
        Assert.assertEquals("更新min_num失败", 1, cs.findCompany(1).getCustomer_info_flag());

    }

    @Test
    public void testUpdateRobotConf() {
        int robot_open_flag = 1;
        Company c1 = cs.findCompany(1);
        cs.updateRobotConf(c1, robot_open_flag);
        Assert.assertEquals("更新Access失败", 1, cs.findCompany(1).getRobot_open_flag());

    }
    
    @Test
    public void testUpdateCustomerService() {
        int min_num=2;
        int company_id=1;
        Company c1 = cs.findCompany(1);
        cs.updateCustomerService( min_num,  company_id);
        for(CustomerService cs:cm.selectCustomerService(company_id)){
            Assert.assertTrue("Update不完全", cs.getCs_operating_number()>=2);     
        }
    }
}
