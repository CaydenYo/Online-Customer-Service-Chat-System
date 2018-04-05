package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.RobotQuestionBean;

public interface RobotChatMapper {
    public int getRobotChatFlagMapper(int company_id);

    public List<RobotQuestionBean> getRobotQuestionMapper(int company_id);
}
