package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.CSViewsCustomerInfoBean;
import com.pentaKill.domain.CustomerInfoBean;

public interface CSViewsCustomerInfoMapper {
    public int searchCustomerInfoFlagMapper(int company_id);

    public int searchCompanyIdMapper(int cs_id);

    public CustomerInfoBean searchCustomerInfoMapper(int customer_id);
    
    public List<CSViewsCustomerInfoBean> searchCurrentTime(int cs_id);
}
