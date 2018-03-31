package com.pentaKill.controller;

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
import com.pentaKill.domain.CustomerService;
import com.pentaKill.domain.CustomerServiceRegisterBean;
import com.pentaKill.domain.CustomerServiceStatusBean;
import com.pentaKill.service.CustomerServiceService;

import net.sf.json.JSONObject;

@Controller
public class CustomerServiceMController {
	
	@Resource
	private CustomerServiceService customerServiceService;
	
	@RequestMapping(value = "/listCSInfo",produces = "text/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String listCSInfo(HttpServletRequest request,HttpServletResponse response){
		System.out.println(1);
		String param = request.getParameter("data");
		System.out.println(param);
		Gson gson = new Gson();
		CustomerService cs = gson.fromJson(param, CustomerService.class);
		System.out.println(cs.getCs_name());
		return null;
	}
	
	
	@RequestMapping(value = "reg.do")  
    public ModelAndView reg(ModelAndView mv, HttpServletRequest request) {  
        CustomerServiceRegisterBean customerServiceRegisterBean = new CustomerServiceRegisterBean();  
        customerServiceRegisterBean.setCs_email(request.getParameter("cs_email"));  
        customerServiceRegisterBean.setCs_workId(request.getParameter("cs_workId")); 
        if (customerServiceService.reg(customerServiceRegisterBean)) {  
            mv.setViewName("/userlogin");  
        } else {  
            mv.addObject("msg", "注册失败");  
            mv.setViewName("/index");  
        }  
        return mv;  
    }  
    
    
    @RequestMapping(value = "regconf.do")  
    public ModelAndView regconf(ModelAndView mv, HttpServletRequest request) {  
        String code = request.getParameter("cs_code");  
        customerServiceService.regconf(code);  
        mv.setViewName("/userlogin.jsp");  
        return mv;  
    }
	
	
	
	@RequestMapping(value = "/listCsStatus",produces = "text/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String listCsStatus(HttpServletRequest request,HttpServletResponse response){
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
	
	@RequestMapping(value = "/listOperatingNum",produces = "text/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String listOperatingNum(HttpServletRequest request,HttpServletResponse response){
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
	
	@RequestMapping(value = "/listWaitingNum",produces = "text/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String listWaitingNum(HttpServletRequest request,HttpServletResponse response){
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
	
	
	
	
	
	
	
	
	
	
}
