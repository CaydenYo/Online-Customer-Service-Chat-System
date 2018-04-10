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

    public void update(Company company, int dstribution_type, int customer_info_flag, int access_type, int min_num) {
        company.setMininum_operating_num(min_num);
        company.setDstribution_type(dstribution_type);
        company.setCustomer_info_flag(customer_info_flag);
        company.setAccess_type(access_type);
        companyMapper.UpdateCompany(company);
    }

    public CustomerService selectCustomerService(Company company) {
        int company_id = company.getCompany_id();
        int min_num = company.getMininum_operating_num();
        return companyMapper.selectCustomerService(company_id, min_num);
    }

    public void updateCustomerService(CustomerService customerService, int min_num) {
        int cs_id = customerService.getCs_id();
        companyMapper.updateCustomerService(min_num, cs_id);
    }
}
