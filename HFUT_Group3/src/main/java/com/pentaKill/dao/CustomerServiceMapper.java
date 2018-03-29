package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceStatusBean;

public interface CustomerServiceMapper {

	public CustomerService selectBy(CustomerService customerService);

	public List<CustomerServiceStatusBean> getCustomerServiceStatus(int company_id);

	public int getTotalOperatingNum(int company_id);

	public int getTotalWaitingNum(int company_id);
}
