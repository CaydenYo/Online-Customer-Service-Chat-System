package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.Company;
import com.pentaKill.domain.CustomerService;

public interface CompanyMapper {

    public Company findCompany(int company_id);

    public void updateCompany(Company company);

    public List<CustomerService> selectCustomerService(int company_id);

    public void updateCustomerService(int min_num, int company_id);

}
