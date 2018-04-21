package com.pentaKill.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pentaKill.dao.CustomerServiceMapper;
import com.pentaKill.domain.Company;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceLoginBean;
import com.pentaKill.domain.FastReplyBean;
import com.pentaKill.domain.WaitingQueueCustomerInfo;
import com.pentaKill.exception.LoginException;
import com.pentaKill.service.CompanyService;
import com.pentaKill.service.CustomerServiceService;
import com.pentaKill.service.FastReplyService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class CustomerServiceController {
    @Resource
    private CustomerServiceService customerSvcService;

    @Resource
    private CompanyService companyService;

    @Resource
    private FastReplyService fastReplyService;

    @RequestMapping(value = "/customerService/Login", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String csLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        String csEmail = json.getString("cs_email");
        String csPwd = json.getString("cs_pwd");
        CustomerServiceLoginBean customerServiceLoginBean = new CustomerServiceLoginBean(csEmail, csPwd);
        CustomerService customerService = new CustomerService();
        try {
            customerService = customerSvcService.csLogin(customerServiceLoginBean);
            if (customerService != null) {// 如果验证成功，则跳转进首页
                if (customerService.getCs_status() == 0) {
                    return gson.toJson("客服账号未激活");
                } else {
                    // 登陆后状态为工作
                    customerSvcService.setStatus(1, customerService);
                    return gson.toJson(customerService);
                }
            } else {
                return gson.toJson("用户名或者密码错误");
            }
        } catch (LoginException e) {
            return gson.toJson("用户名或者密码错误");
        }
    }

    @RequestMapping(value = "/customerService/showProfile", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String showProfile(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        String csEmail = json.getString("cs_email");

        CustomerService customerService = customerSvcService.selectByEmail(csEmail);
        return gson.toJson(customerService);
    }

    @RequestMapping(value = "/customerService/setProfile", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String setProfile(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        String csImg = json.getString("cs_img");
        String csName = json.getString("cs_name");
        String csNickName = json.getString("cs_nickName");
        String csPwd = json.getString("cs_pwd");
        String csEmail = json.getString("cs_email");

        CustomerService customerService = new CustomerService();
        // 要先拿出来，其他数据不能动
        customerService = customerSvcService.selectByEmail(csEmail);
        customerSvcService.setProfile(csPwd, csImg, csNickName, csName, customerService);
        return gson.toJson("success");
    }

    @RequestMapping(value = "/customerService/setNumber", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String setNumber(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int csWaitingNumber = json.getInt("cs_waiting_number");
        int csOperatingNumber = json.getInt("cs_operating_number");
        int companyId = json.getInt("company_id");
        String csEmail = json.getString("cs_email");

        Company company = companyService.findCompany(companyId);
        int minimum = company.getMininum_operating_num();
        // 设置的人数小于公司最小人数
        if (csOperatingNumber < minimum) {
            String str = gson.toJson(minimum);
            return str;
        }

        CustomerService customerService = new CustomerService();
        // 要先拿出来，其他数据不能动
        customerService = customerSvcService.selectByEmail(csEmail);
        customerSvcService.setNumber(csOperatingNumber, csWaitingNumber, customerService);
        return gson.toJson("success");
    }

    @RequestMapping(value = "/customerService/setStatus", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String setStatus(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int csStatus = json.getInt("cs_status");
        String csEmail = json.getString("cs_email");

        CustomerService customerService = new CustomerService();
        // 要先拿出来，其他数据不能动
        customerService = customerSvcService.selectByEmail(csEmail);
        customerSvcService.setStatus(csStatus, customerService);
        return gson.toJson("success");
    }

    @RequestMapping(value = "/customerService/showFastReply", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String showOneFastReply(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int csId = json.getInt("cs_id");

        List<FastReplyBean> fastReplyList = fastReplyService.showFastReplyService(csId);
        // for(FastReplyBean i:fastReplyList){
        // System.out.println(i);
        // }
        return gson.toJson(fastReplyList);
    }

    @RequestMapping(value = "/customerService/deleteOneFastReply", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String deleteOneFastReply(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int shortcutLanguageId = json.getInt("shortcut_language_id");

        fastReplyService.deleteFastReplyService(shortcutLanguageId);
        return gson.toJson("DeleteSuccess");
    }

    @RequestMapping(value = "/customerService/deleteFastReply", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFastReply(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONArray jsonArray = JSONArray.fromObject(data);
        List<FastReplyBean> fastReplyList = (List) JSONArray.toCollection(jsonArray, FastReplyBean.class);
        for (FastReplyBean fastReply : fastReplyList) {
            fastReplyService.deleteFastReplyService(fastReply.getShortcut_language_id());
        }
        return gson.toJson("DeleteSuccess");
    }

    @RequestMapping(value = "/customerService/modifyFastReply", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String modifyOneFastReply(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int shortcutLanguageId = json.getInt("shortcut_language_id");
        String content = json.getString("content");
        fastReplyService.modifyFastReplyService(shortcutLanguageId, content);

        return gson.toJson("ModifySuccess");
    }

    @RequestMapping(value = "/customerService/addFastReply", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String addFastReply(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int csId = json.getInt("cs_id");
        String content = json.getString("content");
        FastReplyBean fastReplyBean = new FastReplyBean();
        fastReplyBean.setContent(content);
        fastReplyBean.setCs_id(csId);
        fastReplyService.addFastReplyService(fastReplyBean);
        return gson.toJson("AddSuccess");
    }

    @RequestMapping(value = "/customerService/showTimeAndScore", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String showTimeAndScore(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int csId = json.getInt("cs_id");
        int csCountToday = customerSvcService.getCountToday(csId);
        int csCount = customerSvcService.getCount(csId);
        int csScore = customerSvcService.getScore(csId);
        int csTime = customerSvcService.getTime(csId);
        List<Integer> list = new LinkedList<Integer>();
        list.add(csCount);
        list.add(csTime);
        list.add(csScore);
        list.add(csCountToday);
        Gson gson = new Gson();
        return gson.toJson(list);
    }
    
    //客服查看排队列表
    @RequestMapping(value = "/customerService/showWaitingQueue", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String showWaitingQueue(HttpServletRequest request, HttpServletResponse response){
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int csId =  json.getInt("cs_id");
        List<Integer> list = new LinkedList<Integer>();
        list = customerSvcService.getWaitingQueue(csId);
        List<WaitingQueueCustomerInfo> customerInfo = new LinkedList<WaitingQueueCustomerInfo>();
        WaitingQueueCustomerInfo info;
        for(Integer i : list){
            info = customerSvcService.getCustomerInfo(i.intValue());
            customerInfo.add(info);
        }
        System.out.println(customerInfo.toString());
        Gson gson = new Gson();
        return gson.toJson(customerInfo);
    }
    
    //客服点击客服将改变客户的状态
    @RequestMapping(value = "/customerService/deleteWaitingQueue", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String changeWaitingQueue(HttpServletRequest request, HttpServletResponse response){
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int customerId =  json.getInt("customer_id");
        customerSvcService.deleteWaitingCustomer(customerId);
        return null;
    }
}
