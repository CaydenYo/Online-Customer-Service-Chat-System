package com.pentaKill.dao;

import com.pentaKill.domain.CSViewsCustomerInfoBean;

public interface CSViewsCustomerInfoMapper {
    public int searchCustomerInfoFlag_mapper(int company_id);
    
    public int searchCompanyId_mapper(int cs_id);
    
    public CSViewsCustomerInfoBean  searchCustomerInfo_mapper(int customer_id);
}
