package com.pentaKill.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.pentaKill.domain.Company;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceRegisterBean;
import com.pentaKill.domain.CustomerServiceStatusBean;
import com.pentaKill.domain.ListCSInfoBean;
import com.pentaKill.service.CompanyService;
import com.pentaKill.service.CustomerServiceService;

import net.sf.json.JSONObject;

@Controller
public class CustomerServiceMController {

    @Resource
    private CustomerServiceService customerServiceService;
    @Resource
    private CompanyService companyService;

    @RequestMapping(value = "/listCSInfo", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listCSInfo(HttpServletRequest request, HttpServletResponse response) {
        // System.out.println(1);
        String param = request.getParameter("data");
        // System.out.println(param);
        Gson gson = new Gson();
        CustomerService cs = gson.fromJson(param, CustomerService.class);
        // System.out.println(cs.getCs_name());
        return null;
    }

    @RequestMapping(value = "reg.do", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    public String reg(ModelAndView mv, HttpServletRequest request) {
        CustomerServiceRegisterBean customerServiceRegisterBean = new CustomerServiceRegisterBean();
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        String cs_email = json.getString("cs_email");
        String cs_workId = json.getString("cs_workId");
        int company_id = json.getInt("company_id");
        customerServiceRegisterBean.setCs_email(request.getParameter(cs_email));
        customerServiceRegisterBean.setCs_workId(request.getParameter(cs_workId));
        customerServiceRegisterBean.setCompany_id(company_id);
        ;
        Gson gson = new Gson();
        if (customerServiceService.reg(customerServiceRegisterBean)) {
            return gson.toJson("registersuccess");
        } else {
            return gson.toJson("registerfail");
        }
    }

    @RequestMapping(value = "regconf.do")
    public String regconf(ModelAndView mv, HttpServletRequest request) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        String cs_code = json.getString("cs_code");
        customerServiceService.regconf(cs_code);
        Gson gson = new Gson();
        return gson.toJson("login");
    }

    @RequestMapping(value = "/listCsStatus", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listCsStatus(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int company_id = json.getInt("company_id");
        System.out.println(company_id);
        Gson gson = new Gson();
        List<CustomerServiceStatusBean> statusList = customerServiceService.listCustomerSStatus(company_id);
        String str = gson.toJson(statusList);
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/listOperatingNum", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listOperatingNum(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int company_id = json.getInt("company_id");
        System.out.println(company_id);
        Gson gson = new Gson();
        int operatingNum = customerServiceService.listTotalOperatingNum(company_id);
        String str = gson.toJson(operatingNum);
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/listWaitingNum", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listWaitingNum(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int company_id = json.getInt("company_id");
        System.out.println(company_id);
        Gson gson = new Gson();
        int operatingNum = customerServiceService.listTotalWaitingNum(company_id);
        String str = gson.toJson(operatingNum);
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/setCompanyInfo", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String setCompanyInfo(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int company_id = json.getInt("company_id");
        boolean distribution_type = json.getBoolean("distribution_type");
        boolean customer_info_flag = json.getBoolean("customer_info_flag");
        boolean access_type = json.getBoolean("access_type");
        int int_distribution_type = (distribution_type==true?1:0);
        int int_customer_info_flag = (customer_info_flag==true?1:0);
        int int_access_type = (access_type==true?1:0);
        int min_num = json.getInt("mininum_operating_num");
        Company company = companyService.findCompany(company_id);
      
        companyService.update(company, int_distribution_type, int_customer_info_flag, int_access_type, min_num);

        // 小于min_num的客服进行刷新
        CustomerService customerService = companyService.selectCustomerService(company);
        companyService.updateCustomerService(customerService, min_num);
        Gson gson = new Gson();
        return gson.toJson("UpdateSuccess");

    }
    
    @RequestMapping(value = "/ListCSInfoList", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listCSInfoList(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int company_id = json.getInt("company_id");
        List<ListCSInfoBean> list = new LinkedList<ListCSInfoBean>();
        list = customerServiceService.getCSInfoList(company_id);
        Gson gson = new Gson();
        return gson.toJson("UpdateSuccess");

    }

}
