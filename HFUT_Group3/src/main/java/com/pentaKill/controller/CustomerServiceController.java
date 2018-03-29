package com.pentaKill.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceLoginBean;
import com.pentaKill.exception.LoginException;
import com.pentaKill.service.CustomerServiceService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/customerService")
public class CustomerServiceController {
	@Resource
	private CustomerServiceService customerSvcService;

	@RequestMapping(value = "/Login", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String csLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();

		String data = request.getParameter("data");
		JSONObject json = JSONObject.fromObject(data);
		String cs_email = json.getString("cs_email");
		String cs_pwd = json.getString("cs_pwd");
		CustomerServiceLoginBean customerServiceLoginBean = new CustomerServiceLoginBean(cs_email, cs_pwd);
		CustomerService customerService;
		try {
			customerService = customerSvcService.csLogin(customerServiceLoginBean);
			if (customerService != null) {// 如果验证成功，则跳转进首页
				if (customerService.getCs_status() == 0) {
					System.out.println("yyy");
					return gson.toJson("客服账号未激活");
				} else {
					return gson.toJson("success");
				}
			} else {
				return gson.toJson("用户名或者密码错误");
			}
		} catch (LoginException e) {
			//System.out.println(e.getMessage());
			return gson.toJson("用户名或者密码错误");
		}
	}

}
