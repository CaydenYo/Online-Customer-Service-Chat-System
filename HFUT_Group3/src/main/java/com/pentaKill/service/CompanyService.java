package com.pentaKill.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pentaKill.dao.CompanyMapper;
import com.pentaKill.domain.Company;
import com.pentaKill.domain.CustomerService;

@Service
public class CompanyService {

    @Resource
    CompanyMapper companyMapper;

    public Company findCompany(int company_id) {
        return companyMapper.findCompany(company_id);
    }

    public void updateAccess(Company company, int access_type){
        company.setAccess_type(access_type);
        companyMapper.updateCompany(company); 
    }
    
    public void update(Company company, int dstribution_type, int customer_info_flag, int min_num) {
        company.setMininum_operating_num(min_num);
        company.setDstribution_type(dstribution_type);
        company.setCustomer_info_flag(customer_info_flag);
        companyMapper.updateCompany(company);
    }
    
    public void updateRobotConf(Company company, int robot_open_flag) {
        company.setRobot_open_flag(robot_open_flag);
        companyMapper.updateCompany(company);
    }

    public CustomerService selectCustomerService(Company company) {
        int company_id = company.getCompany_id();
        int min_num = company.getMininum_operating_num();
        return companyMapper.selectCustomerService(company_id, min_num);
    }

    public void updateCustomerService(int min_num, int company_id) {
        companyMapper.updateCustomerService(min_num, company_id);
    }
}
