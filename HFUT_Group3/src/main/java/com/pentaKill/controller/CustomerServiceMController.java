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
        String csEmail = json.getString("cs_email");
        String csWorkId = json.getString("cs_workId");
        int companyId = json.getInt("company_id");
        customerServiceRegisterBean.setCs_email(csEmail);
        customerServiceRegisterBean.setCs_workId(csWorkId);
        customerServiceRegisterBean.setCompany_id(companyId);

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
        String csCode = json.getString("cs_code");
        customerServiceService.regconf(csCode);
        Gson gson = new Gson();
        return gson.toJson("login");
    }

    @RequestMapping(value = "/listCsStatus", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listCsStatus(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        System.out.println(companyId);
        Gson gson = new Gson();
        List<CustomerServiceStatusBean> statusList = customerServiceService.listCustomerSStatus(companyId);
        String str = gson.toJson(statusList);
        System.out.println(str);
        return str;
    }
    
    //显示正在操作的对话
    @RequestMapping(value = "/listOperatingNum", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listOperatingNum(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        System.out.println(companyId);
        Gson gson = new Gson();
        int operatingNum = customerServiceService.listTotalOperatingNum(companyId);
        String str = gson.toJson(operatingNum);
        System.out.println(str);
        return str;
    }
    
    //显示正在等待的对话
    @RequestMapping(value = "/listWaitingNum", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listWaitingNum(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        System.out.println(companyId);
        Gson gson = new Gson();
        int waitingNum = customerServiceService.listTotalWaitingNum(companyId);
        String str = gson.toJson(waitingNum);
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/setCompanyInfo", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String setCompanyInfo(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        boolean distributionType = json.getBoolean("distribution_type");
        boolean customerInfoFlag = json.getBoolean("customer_info_flag");
        boolean accessType = json.getBoolean("access_type");
        int intDistributionType = (distributionType==true?1:0);
        int intCustomerInfoFlag = (customerInfoFlag==true?1:0);
        int intAccessType = (accessType==true?1:0);
        int minNum = json.getInt("mininum_operating_num");
        Company company = companyService.findCompany(companyId);
      
        companyService.update(company, intDistributionType, intCustomerInfoFlag, intAccessType, minNum);

        // 小于min_num的客服进行刷新
        CustomerService customerService = companyService.selectCustomerService(company);
        companyService.updateCustomerService(customerService, minNum);
        Gson gson = new Gson();
        return gson.toJson("UpdateSuccess");

    }
    
    @RequestMapping(value = "/ListCSInfoList", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listCSInfoList(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        List<ListCSInfoBean> list = new LinkedList<ListCSInfoBean>();
        list = customerServiceService.getCSInfoList(companyId);
        Gson gson = new Gson();
        return gson.toJson("UpdateSuccess");

    }
    
    //概况显示客服总数、在线客服数、接入会话数、正在等待数
    @RequestMapping(value = "/listNumbers", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listNumbers(HttpServletRequest request, HttpServletResponse response){
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        //接入会话数
        int operatingNum = customerServiceService.listTotalOperatingNum(companyId);
        //正在等待数
        int waitingNum = customerServiceService.listTotalWaitingNum(companyId);
        //得到客服总数
        int customerServiceNum = customerServiceService.countServiceNum(companyId);
        //得到在线客服总数
        int customerServiceOnlineNum = customerServiceService.countOnlineServiceNum(companyId);
        List<Integer> list = new LinkedList<Integer>();
        list.add(operatingNum);
        list.add(waitingNum);
        list.add(customerServiceNum);
        list.add(customerServiceOnlineNum);
        Gson gson = new Gson();
        System.out.println(gson.toJson(list));
        return gson.toJson(list);
    }

}
