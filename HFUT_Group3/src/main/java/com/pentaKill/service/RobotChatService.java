package com.pentaKill.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.RobotChatMapper;
import com.pentaKill.domain.RobotQuestionBean;

@Service
@Scope
public class RobotChatService {
    @Resource
    RobotChatMapper robotChatMapper;

    public int getRobotFlagService(int company_id) {
        int temp = robotChatMapper.getRobotChatFlagMapper(company_id);
        return temp;
    }

    public List<RobotQuestionBean> getRobotQuestionService(int company_id) {
        List<RobotQuestionBean> temp = robotChatMapper.getRobotQuestionMapper(company_id);
        return temp;
    }

}
