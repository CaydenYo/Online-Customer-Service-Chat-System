package com.pentaKill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerSvcLogin;
import com.pentaKill.exception.LoginException;
import com.pentaKill.filter.CustomerSvcLoginFilter;
import com.pentaKill.service.CustomerServiceService;

@Controller
@RequestMapping(value = "/customerService")
public class CustomerServiceController {
	@Resource
	private CustomerServiceService customerSvcService;

	@RequestMapping(value = "/Login", method = { RequestMethod.GET, RequestMethod.POST })
	public String customerSvcLogin(@Valid @ModelAttribute("CustomerSvcLogin") CustomerSvcLogin customerSvcLogin,
			Errors errors, HttpSession session) {

		if (errors.hasFieldErrors())
			return "customerService/Login";
		CustomerService customerService;
		try {
			customerService = customerSvcService.csLogin(customerSvcLogin);
			System.out.println(customerService);
			if (customerService.getCs_status() == 0) {
				System.out.println("yyy");
				errors.reject("", "客服账号未激活");
				return "customerService/Login";
			}
		} catch (LoginException e) {
			System.out.println(e.getMessage());
			errors.reject("", e.getMessage());
			return "customerService/Login";
		}
		session.setAttribute(CustomerSvcLoginFilter.ATTR_ADMINUSER, customerService);
		session.setAttribute("flags", 0);
		String forwardUrl = (String) session.getAttribute(CustomerSvcLoginFilter.ATTR_NEXTURL);
		if (forwardUrl == null)
			forwardUrl = "/index.jsp";
		return "redirect:" + forwardUrl;
	}

}
