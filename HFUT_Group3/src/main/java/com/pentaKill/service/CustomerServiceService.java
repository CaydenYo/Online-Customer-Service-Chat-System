package com.pentaKill.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CustomerServiceMapper;
import com.pentaKill.domain.CustomerServiceStatusBean;

@Service
@Scope
public class CustomerServiceService {
	
	@Resource
	CustomerServiceMapper customerServiceMapper;
	
	public List<CustomerServiceStatusBean> listCustomerSStatus(int company_id){
		
		List<CustomerServiceStatusBean> list = new LinkedList<CustomerServiceStatusBean>();
		list = customerServiceMapper.getCustomerServiceStatus(company_id);
		return list;
		
	}
	
	public int listTotalOperatingNum(int company_id){
		int operatingNum = customerServiceMapper.getTotalOperatingNum(company_id);
		return operatingNum;
	}
	
	public int listTotalWaitingNum(int company_id){
		int operatingNum = customerServiceMapper.getTotalWaitingNum(company_id);
		return operatingNum;
	}
	
}
