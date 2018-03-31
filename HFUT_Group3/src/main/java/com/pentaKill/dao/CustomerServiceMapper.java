package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceStatusBean;

public interface CustomerServiceMapper {

	public void create(CustomerService customerService);
	
	public CustomerService selectBy(CustomerService customerService);
	
	public CustomerService selectByEmail(CustomerService customerService);
	
	public CustomerService selectByCode(String code);
	
	public void modifyUser(CustomerService customerService);

	public List<CustomerServiceStatusBean> getCustomerServiceStatus(int company_id);

	public int getTotalOperatingNum(int company_id);

	public int getTotalWaitingNum(int company_id);
}
