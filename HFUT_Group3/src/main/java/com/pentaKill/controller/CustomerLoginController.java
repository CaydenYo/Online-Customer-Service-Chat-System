package com.pentaKill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pentaKill.domain.CustomerInfoBean;
import com.pentaKill.service.CustomerLoginService;

@Controller
public class CustomerLoginController {
    @Resource
    HttpServletRequest req;
    @Resource
    CustomerLoginService customerLoginService;

    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public String login() {
        String customer_name = req.getParameter("customer_name");
        String customer_pwd = req.getParameter("customer_pwd");

        CustomerInfoBean clb = customerLoginService.customerLogin_service(customer_name);
        if (clb != null && clb.getCustomer_pwd().equals(customer_pwd)) {
            return "index";
        } else {
            return "login";
        }

    }
}
