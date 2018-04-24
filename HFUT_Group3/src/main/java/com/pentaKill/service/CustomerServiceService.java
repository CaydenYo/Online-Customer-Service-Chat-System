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
import com.pentaKill.domain.CsEvaluateBean;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceStatusBean;
import com.pentaKill.domain.ListCSInfoBean;
import com.pentaKill.domain.WaitingQueueCustomerInfo;
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
        CustomerService customerService = new CustomerService();
        String email = customerServiceRegisterBean.getCs_email();
        customerService.setCs_code(code);
        customerService.setCompany_id(customerServiceRegisterBean.getCompany_id());
        customerService.setCs_email(email);
        customerService.setCs_workId(customerServiceRegisterBean.getCs_workId());
        customerService.setCs_name("pentaKill_" + email);
        customerService.setCs_nickName("pentaKill_" + email);
        customerService.setCs_pwd(customerServiceRegisterBean.getCs_workId());
        customerService.setCs_img("pentaKill_" + email);
        if (customerServiceMapper.selectByEmail(email) == null) {
            customerServiceMapper.create(customerService);
            try {
                StringBuffer sbd = new StringBuffer();
                sbd.append("<br/>欢迎！请确认此邮件地址以激活您的账号。<br/>");
                sbd.append("<font color='red'><a href='http://localhost:8080/OCSSystem/regconf.do?code=" + code
                        + "' target='_blank'");
                sbd.append(">立即激活</a></font><br/>");
                sbd.append("或者点击下面链接:<br/>");
                sbd.append("http://localhost:8080/OCSSystem/regconf.do?code=" + code + "<br/>");
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
        System.out.println(customerService.getCs_register_status());
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

    public void setImg(String cs_img, CustomerService customerService) {
        customerService.setCs_img(cs_img);
        customerServiceMapper.modifyUser(customerService);
    }

    public void setPwd(String cs_pwd, CustomerService customerService) {
        customerService.setCs_pwd(cs_pwd);
        customerServiceMapper.modifyUser(customerService);
    }

    public void setName(String cs_nickName, CustomerService customerService) {
        customerService.setCs_nickName(cs_nickName);
        customerServiceMapper.modifyUser(customerService);
    }

    public void insertNewEvaluateService(CsEvaluateBean csb) {
        customerServiceMapper.insertNewEvaluateMapper(csb);
    }

    public List<ListCSInfoBean> getCSInfoList(int company_id) {
        List<ListCSInfoBean> list = new LinkedList<ListCSInfoBean>();
        list = customerServiceMapper.getCSInfoList(company_id);
        return list;
    }

    public int countServiceNum(int company_id) {
        int num = customerServiceMapper.countServiceNum(company_id);
        return num;
    }

    public int countOnlineServiceNum(int company_id) {
        int num = customerServiceMapper.countOnlineServiceNum(company_id);
        return num;
    }

    public int getCountToday(int cs_id) {
        int count = customerServiceMapper.getCountToday(cs_id);
        return count;
    }

    public int getCount(int cs_id) {
        int count = customerServiceMapper.getCount(cs_id);
        return count;
    }

    public int getScore(int cs_id) {
        double count = customerServiceMapper.getScore(cs_id);
        int score = (int) Math.round(count * 10);
        return score;
    }

    public int getTime(int cs_id) {
        int count = customerServiceMapper.getTime(cs_id);
        return count;
    }

    public List<Integer> getWaitingQueue(int cs_id) {
        List<Integer> list = new LinkedList<Integer>();
        list = customerServiceMapper.getWaitingQueue(cs_id);
        return list;
    }

    public WaitingQueueCustomerInfo getCustomerInfo(int customer_id) {
        WaitingQueueCustomerInfo info = customerServiceMapper.getCustomerInfo(customer_id);
        return info;
    }

    public void deleteWaitingCustomer(int customer_id) {
        customerServiceMapper.deleteWaitingCustomer(customer_id);
    }

}
