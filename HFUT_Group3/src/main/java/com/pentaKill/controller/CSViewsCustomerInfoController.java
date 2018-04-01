package com.pentaKill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/csViewsCustomerInfo.action")
    public String customerRegiste() {

        String data = req.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int cs_id = Integer.parseInt("cs_id");
        int customer_id = Integer.parseInt("customer_id");

        // 查询客服对应的公司
        int company_id = csViewsCustomerInfoService.searchCompanyId_service(cs_id);
        // 查询客服是否能查看客户信息
        int customer_info_flag = csViewsCustomerInfoService.searchCustomerInfoFlag_service(company_id);
        Gson gson = new Gson();
        if (customer_info_flag == 0) {
            return gson.toJson("fail");
        } else {
            CSViewsCustomerInfoBean temp = csViewsCustomerInfoService.searchCustomerInfo_service(customer_id);
            return gson.toJson(temp);
        }
    }
}
