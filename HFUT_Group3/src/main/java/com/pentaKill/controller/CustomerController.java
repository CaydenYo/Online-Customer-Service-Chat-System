package com.pentaKill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pentaKill.domain.CustomerRegisterBean;
import com.pentaKill.service.CustomerRegisterService;

@Controller
public class CustomerController {
    @Resource
    CustomerRegisterService customerRegisterService;

    @Resource
    HttpServletRequest req;

    @RequestMapping(value = "/register.action")
    public String customerRegiste() {

        String customer_name = req.getParameter("customer_name");
        int customer_sex = -1;
        if (req.getParameter("customer_sex") == "man") {
            customer_sex = 1;
        } else {
            customer_sex = 0;
        }
        String customer_email = req.getParameter("customer_email");
        String customer_pwd = req.getParameter("customer_pwd");
        String conpass = req.getParameter("conpass");
        String customer_address = req.getParameter("customer_address");
        CustomerRegisterBean crb = new CustomerRegisterBean(customer_name, customer_sex, customer_email, customer_pwd,
                customer_address);
        customerRegisterService.customerRegister_service(crb);
        // 返回User的主页

        return "index";

    }

}
