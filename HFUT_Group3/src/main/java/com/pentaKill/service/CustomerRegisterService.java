package com.pentaKill.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pentaKill.dao.CustomerRegisterMapper;
import com.pentaKill.domain.CustomerRegisterBean;



@Service
public class CustomerRegisterService {
	@Resource
	CustomerRegisterMapper customerRegisterMapper;
	
	public void customerRegister_service(CustomerRegisterBean crb){
		customerRegisterMapper.customerRegister_mapper(crb);
	}
}
