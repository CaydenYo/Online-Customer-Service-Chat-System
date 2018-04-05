package com.pentaKill.dao;

import com.pentaKill.domain.CSViewsCustomerInfoBean;

public interface CSViewsCustomerInfoMapper {
    public int searchCustomerInfoFlagMapper(int company_id);

    public int searchCompanyIdMapper(int cs_id);

    public CSViewsCustomerInfoBean searchCustomerInfoMapper(int customer_id);
}
