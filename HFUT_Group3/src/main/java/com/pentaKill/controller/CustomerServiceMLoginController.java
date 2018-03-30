package com.pentaKill.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pentaKill.domain.CSManager;
import com.pentaKill.domain.CSManagerLoginBean;
import com.pentaKill.exception.LoginException;
import com.pentaKill.service.CustomerServiceMService;

import net.sf.json.JSONObject;

public class CustomerServiceMLoginController {
	@Resource
	private CustomerServiceMService customerServiceMService;
	
	@RequestMapping(value = "/Login", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String csmLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();

		String data = request.getParameter("data");
		JSONObject json = JSONObject.fromObject(data);
		String csm_email = json.getString("csm_email");
		String csm_pwd = json.getString("csm_pwd");
		CSManagerLoginBean csManagerLoginBean = new CSManagerLoginBean(csm_email, csm_pwd);
		CSManager csManager;
		try {
			csManager = customerServiceMService.csmLogin(csManagerLoginBean);
			if (csManager != null) {// 如果验证成功，则跳转进首页
					return gson.toJson("success");
			} else {
				return gson.toJson("用户名或者密码错误");
			}
		} catch (LoginException e) {
			//System.out.println(e.getMessage());
			return gson.toJson("用户名或者密码错误");
		}
	}
	
	
	@RequestMapping(value="/Register", method=RequestMethod.POST)
	public String cmResgister(@Valid @ModelAttribute("csManager")CSManager  csManager, 
    		Errors errors, HttpSession session) {
    	if (errors.hasFieldErrors()) return "register";
    	try {
    		customerServiceMService.csmRegister(csManager);
    		return "success";
    	} catch (Exception e) {
    		errors.reject("", e.getMessage());
            return "register";
    	}

	}
}
