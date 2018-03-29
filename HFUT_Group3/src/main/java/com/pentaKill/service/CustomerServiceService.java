package com.pentaKill.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CustomerServiceMapper;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceStatusBean;
import com.pentaKill.domain.CustomerSvcLogin;
import com.pentaKill.exception.LoginException;

@Service
@Scope
public class CustomerServiceService {

	@Resource
	CustomerServiceMapper customerServiceMapper;

	public CustomerService csLogin(CustomerSvcLogin customerSvcLogin) throws LoginException {
		CustomerService customerService = new CustomerService();
		customerService.setCs_email(customerSvcLogin.getCs_email());
		customerService.setCs_pwd(customerSvcLogin.getCs_pwd());
		try {
			customerService = customerServiceMapper.selectBy(customerService);
		} catch (Exception e) {
			throw new LoginException(e);
		}
		if (customerService == null) {
			throw new LoginException("用户名不存在或密码错误");
		}
		return customerService;
	}

	public List<CustomerServiceStatusBean> listCustomerSStatus(int company_id) {

		List<CustomerServiceStatusBean> list = new LinkedList<CustomerServiceStatusBean>();
		list = customerServiceMapper.getCustomerServiceStatus(company_id);
		return list;
	}

	public int listTotalOperatingNum(int company_id) {
		int operatingNum = customerServiceMapper.getTotalOperatingNum(company_id);
		return operatingNum;
	}

	public int listTotalWaitingNum(int company_id) {
		int operatingNum = customerServiceMapper.getTotalWaitingNum(company_id);
		return operatingNum;
	}

}
