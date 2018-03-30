package com.pentaKill.service;

import javax.annotation.Resource;

import com.pentaKill.dao.CustomerServiceMMapper;
import com.pentaKill.domain.CSManager;
import com.pentaKill.domain.CSManagerLoginBean;

import com.pentaKill.exception.LoginException;

public class CustomerServiceMService {
	@Resource
	CustomerServiceMMapper customerServiceMMapper;

	public CSManager csmLogin(CSManagerLoginBean csManagerLoginBean) throws LoginException {
		CSManager csManager = new CSManager();
		csManager.setCsm_email(csManagerLoginBean.getCsm_email());
		csManager.setCsm_pwd(csManagerLoginBean.getCsm_pwd());
		try {
			csManager = customerServiceMMapper.selectBy(csManager);
		} catch (Exception e) {
			throw new LoginException(e);
		}
		if (csManager == null) {
			return null;
		}
		return csManager;
	}
}
