package com.pentaKill.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pentaKill.dao.CustomerServiceMMapper;
import com.pentaKill.domain.CSManager;
import com.pentaKill.domain.CSManagerLoginBean;

import com.pentaKill.exception.LoginException;
import com.pentaKill.exception.RegisterException;

@Service
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

    public void csmRegister(CSManager csManager) throws RegisterException {

            if (customerServiceMMapper.selectBy(csManager) == null)
                customerServiceMMapper.insert(csManager);
            else
                throw new RegisterException("用户名已存在");
    }
}
