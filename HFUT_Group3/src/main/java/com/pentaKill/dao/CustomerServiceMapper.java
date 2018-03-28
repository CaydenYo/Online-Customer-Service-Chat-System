package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.CustomerServiceStatusBean;

public interface CustomerServiceMapper {
	
	public List<CustomerServiceStatusBean> getCustomerServiceStatus(int company_id);

}
