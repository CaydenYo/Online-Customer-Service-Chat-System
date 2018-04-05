package com.pentaKill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pentaKill.dao.CustomerServiceMapper;
import com.pentaKill.domain.CsEvaluateBean;
import com.pentaKill.service.CustomerServiceService;

import net.sf.json.JSONObject;

@Controller
public class CSEvaluateController {
    @Resource
    CustomerServiceService customerServiceService;

    @Resource
    HttpServletRequest req;

    @RequestMapping(value = "/csEvaluate.action")
    public String csEvaluate() {
        String data = req.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        // 传入以下信息
        int cs_id = Integer.parseInt(json.getString("cs_id"));
        float cs_score = Float.parseFloat(json.getString("cs_score"));
        String content = json.getString("content");
        // 时间在这里就不存了，用默认的
        CsEvaluateBean csb = new CsEvaluateBean(cs_id, null, cs_score, content);
        customerServiceService.insertNewEvaluateService(csb);
        Gson gson = new Gson();

        return gson.toJson("success");

    }
}
