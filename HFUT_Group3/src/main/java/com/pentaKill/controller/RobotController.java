package com.pentaKill.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pentaKill.domain.CustomerServiceStatusBean;
import com.pentaKill.domain.RobotKnowledge;
import com.pentaKill.domain.UpdateKnowledgeBean;
import com.pentaKill.service.RobotService;

import net.sf.json.JSONObject;

@Controller
public class RobotController {

    @Resource
    private RobotService robotService;

    @RequestMapping(value = "/insertKnowledge", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listCsStatus(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int company_id = json.getInt("company_id");
        String question = json.getString("question");
        String answer = json.getString("answer");
        int robot_id = robotService.getRobotId(company_id);
        System.out.println(robot_id);
        RobotKnowledge robotKnowledge = new RobotKnowledge(1, robot_id, question, answer);
        robotService.insertKnowledge(robotKnowledge);
        Gson gson = new Gson();
        String str = gson.toJson("success");
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/listAllKnowledge", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String listAllKnowledge(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int company_id = json.getInt("company_id");
        int robot_id = robotService.getRobotId(company_id);
        System.out.println(robot_id);
        List<RobotKnowledge> list = robotService.getRobotAllKnowledge(robot_id);
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/updateKnowledge", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateKnowledge(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int knowledge_id = json.getInt("knowledge_id");
        String question = json.getString("question");
        String answer = json.getString("answer");
        UpdateKnowledgeBean updateKnowledgeBean = new UpdateKnowledgeBean(knowledge_id, question, answer);
        System.out.println(knowledge_id);
        robotService.updateKnowledge(updateKnowledgeBean);
        Gson gson = new Gson();
        String str = gson.toJson("success");
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/deleteKnowledge", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String deleteKnowledge(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        int knowledge_id = json.getInt("knowledge_id");
        System.out.println(knowledge_id);
        robotService.deleteKnowledge(knowledge_id);
        Gson gson = new Gson();
        String str = gson.toJson("success");
        System.out.println(str);
        return str;
    }
    
    //使用excel表插入知识
    @RequestMapping(value = "/insertMuchKnowledge", produces = "text/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String insertMuchKnowledge(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("data");
        Gson gson = new Gson();
        List<RobotKnowledge> list = gson.fromJson(data, new TypeToken<List<RobotKnowledge>>(){}.getType());
        for(RobotKnowledge rk : list){
            robotService.insertKnowledge(rk);
        }
        String str = gson.toJson("success");
        System.out.println(str);
        return str;
    }

}
