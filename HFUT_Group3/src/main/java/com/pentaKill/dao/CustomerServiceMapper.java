package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.ChooseCustomerServiceBean;
import com.pentaKill.domain.CsEvaluateBean;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceStatusBean;
import com.pentaKill.domain.ListCSInfoBean;

public interface CustomerServiceMapper {

    public void create(CustomerService customerService);

    public CustomerService selectBy(CustomerService customerService);

    public void setRegStatus(CustomerService customerService);

    public CustomerService selectByEmail(String cs_email);

    public CustomerService selectByCode(String code);

    public void modifyUser(CustomerService customerService);

    public List<CustomerServiceStatusBean> getCustomerServiceStatus(int company_id);

    public int getTotalOperatingNum(int company_id);

    public int getTotalWaitingNum(int company_id);

    public void insertNewEvaluateMapper(CsEvaluateBean ceb);
    
    public List<ListCSInfoBean> getCSInfoList(int company_id);
}
