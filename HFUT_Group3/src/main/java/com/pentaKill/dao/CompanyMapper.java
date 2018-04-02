package com.pentaKill.dao;

import com.pentaKill.domain.Company;
import com.pentaKill.domain.CustomerService;

public interface CompanyMapper {
    

    public Company findCompany(int company_id);

    public void UpdateCompany(Company company);
    
    public CustomerService selectCustomerService(int company_id,int min_num);
    
    public void updateCustomerService(int min_num,int cs_id);
    
    
}
