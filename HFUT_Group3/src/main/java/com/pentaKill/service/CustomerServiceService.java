package com.pentaKill.service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.swing.text.DefaultEditorKit.CutAction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CustomerServiceMapper;
import com.pentaKill.domain.ChooseCustomerServiceBean;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceStatusBean;
import com.pentaKill.domain.CustomerServiceLoginBean;
import com.pentaKill.domain.CustomerServiceRegisterBean;
import com.pentaKill.exception.LoginException;
import com.pentaKill.utils.EmailUtil;

@Service
@Scope
public class CustomerServiceService {

    @Resource
    CustomerServiceMapper customerServiceMapper;

    public CustomerService csLogin(CustomerServiceLoginBean customerSvcLoginBean) throws LoginException {
        CustomerService customerService = new CustomerService();
        customerService.setCs_email(customerSvcLoginBean.getCs_email());
        customerService.setCs_pwd(customerSvcLoginBean.getCs_pwd());
        try {
            customerService = customerServiceMapper.selectBy(customerService);
        } catch (Exception e) {
            throw new LoginException(e);
        }
        if (customerService == null) {
            return null;
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

    public boolean reg(CustomerServiceRegisterBean customerServiceRegisterBean) {
        // TODO Auto-generated method stub
        String code = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        code = code.replaceAll("-", "");
        customerServiceRegisterBean.setCs_code(code);
        CustomerService customerService = new CustomerService();
        String email = customerServiceRegisterBean.getCs_email();
        customerService.setCompany_id(customerServiceRegisterBean.getCompany_id());
        customerService.setCs_email(email);
        customerService.setCs_workId("pentaKill_" + email);
        customerService.setCs_name("pentaKill_" + email);
        customerService.setCs_nickName("pentaKill_" + email);
        customerService.setCs_pwd("pentaKill_" + email);
        customerService.setCs_img("pentaKill_" + email);
        if (customerServiceMapper.selectByEmail(email) == null) {
            customerServiceMapper.create(customerService);
            try {

                StringBuffer sbd = new StringBuffer();
                sbd.append("<br/>欢迎！请确认此邮件地址以激活您的账号。<br/>");
                sbd.append("<font color='red'><a href='http://localhost:8080/EBP_TEAM7/regconf.do?code="
                        + customerServiceRegisterBean.getCs_code() + "' target='_blank'");
                sbd.append(">立即激活</a></font><br/>");
                sbd.append("或者点击下面链接:<br/>");
                sbd.append("http://localhost:8080/EBP_TEAM7/regconf.do?code=" + customerServiceRegisterBean.getCs_code()
                        + "<br/>");
                sbd.append("这是一封自动发送的邮件；如果您并未要求但收到这封信件，您不需要进行任何操作。");

                EmailUtil.sendTo(sbd.toString(), customerServiceRegisterBean.getCs_email());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public void regconf(String code) {
        // TODO Auto-generated method stub
        CustomerService customerService = customerServiceMapper.selectByCode(code);
        customerService.setCs_register_status(1);
        customerService.setCs_code(code);
        customerServiceMapper.setRegStatus(customerService);
    }

    public CustomerService selectByEmail(String cs_email) {
        return customerServiceMapper.selectByEmail(cs_email);
    }

    public void setStatus(int cs_status, CustomerService customerService) {
        customerService.setCs_status(cs_status);
        customerServiceMapper.modifyUser(customerService);
    }

    public void setNumber(int cs_operating_number, int cs_waiting_number, CustomerService customerService) {
        customerService.setCs_waiting_number(cs_waiting_number);
        customerService.setCs_operating_number(cs_operating_number);
        customerServiceMapper.modifyUser(customerService);
    }

    public void setProfile(String cs_pwd, String cs_img, String cs_nickName, CustomerService customerService) {
        customerService.setCs_pwd(cs_pwd);
        customerService.setCs_img(cs_img);
        customerService.setCs_nickName(cs_nickName);
        customerServiceMapper.modifyUser(customerService);
    }

}
