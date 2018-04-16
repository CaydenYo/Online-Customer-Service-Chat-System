package com.pentaKill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pentaKill.domain.CSViewsCustomerInfoBean;
import com.pentaKill.service.CSViewsCustomerInfoService;

import net.sf.json.JSONObject;

@Controller
public class CSViewsCustomerInfoController {
    @Resource
    CSViewsCustomerInfoService csViewsCustomerInfoService;

    @Resource
    HttpServletRequest req;

    @RequestMapping(value = "/csViewsCustomerInfo.action", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String csViewsCustomerInfo() {

        String data = req.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int cs_id = Integer.parseInt(json.getString("cs_id"));
        int customer_id = Integer.parseInt(json.getString("customer_id"));

        // 查询客服对应的公司
        int company_id = csViewsCustomerInfoService.searchCompanyIdService(cs_id);
        // 查询客服是否能查看客户信息
        int customer_info_flag = csViewsCustomerInfoService.searchCustomerInfoFlagService(company_id);
        Gson gson = new Gson();
        if (customer_info_flag == 0) {
            return gson.toJson("fail");
        } else {
            CSViewsCustomerInfoBean temp = csViewsCustomerInfoService.searchCustomerInfoService(customer_id);
            return gson.toJson(temp);
        }
    }
}
