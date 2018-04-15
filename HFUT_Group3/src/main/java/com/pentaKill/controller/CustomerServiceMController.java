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

    @RequestMapping(value = "/reg", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    public String reg(HttpServletRequest request, HttpServletResponse response) {
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

    @RequestMapping(value = "/regconf.do")
    public ModelAndView  regconf(ModelAndView mv, HttpServletRequest request) {
        String csCode = request.getParameter("code");  
        customerServiceService.regconf(csCode);
        mv.setViewName("/index");  
        return mv;  
    }
    
    //显示客服在线信息
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
        return gson.toJson(statusList);
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

    
    @RequestMapping(value = "/setRobotConf", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String setRobotConf(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        int robotOpenFlag = json.getInt("robot_open_flag");
        Company company = companyService.findCompany(companyId);
        companyService.updateRobotConf(company, robotOpenFlag);
        Gson gson = new Gson();
        return gson.toJson("UpdateSuccess");
    }
    
    @RequestMapping(value = "/setCompanyInfo", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String setCompanyInfo(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        int distributionType = json.getInt("distribution_type");
        int customerInfoFlag = json.getInt("customer_info_flag");
        int minNum = json.getInt("mininum_operating_num");
        Company company = companyService.findCompany(companyId);
      
        companyService.update(company, distributionType, customerInfoFlag, minNum);
        
        // 小于min_num的客服进行刷新
        companyService.updateCustomerService(minNum,companyId);
        Gson gson = new Gson();
        return gson.toJson("UpdateSuccess");
    }

    @RequestMapping(value = "/setAccessType", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String setAccessType(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        int accessType = json.getInt("access_type");
        Company company = companyService.findCompany(companyId);
        companyService.updateAccess(company, accessType);
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
        return gson.toJson(list);

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
    
    
    @RequestMapping(value = "/initializeSetting", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String initializeSetting(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        System.out.println(data);
        JSONObject json = JSONObject.fromObject(data);
        int companyId = json.getInt("company_id");
        System.out.println(companyId);
        Company company = companyService.findCompany(companyId);
        System.out.println(company);
        List<Integer> list = new LinkedList<Integer>();
        list.add(company.getAccess_type());
        list.add(company.getRobot_open_flag());
        list.add(company.getDstribution_type());
        list.add(company.getCustomer_info_flag());
        list.add(company.getMininum_operating_num());
        Gson gson = new Gson();
        System.out.println(gson.toJson(list));
        return gson.toJson(list);
    }

}
