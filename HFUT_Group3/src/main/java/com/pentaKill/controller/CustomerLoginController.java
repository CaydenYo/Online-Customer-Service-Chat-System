package com.pentaKill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pentaKill.domain.CustomerInfoBean;
import com.pentaKill.domain.CustomerLoginBean;
import com.pentaKill.service.CustomerLoginService;

import net.sf.json.JSONObject;

@Controller
public class CustomerLoginController {

    @Resource
    CustomerLoginService customerLoginService;

    @Resource
    HttpServletRequest req;

    @RequestMapping(value = "/login.action", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String login() {

        String data = req.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        String customer_email = json.getString("customer_email");
        String customer_pwd = json.getString("customer_pwd");
        
        CustomerLoginBean clb = customerLoginService.selectCustomerInfo(customer_email);
        Gson gson = new Gson();
        if (clb != null && clb.getCustomer_pwd().equals(customer_pwd)) {
            return gson.toJson(clb);
        } else {
            return gson.toJson("error");
        }

    }
}
