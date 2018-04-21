/**
 * Hyc
 *2018年4月21日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.RobotQuestionBean;

/**
 * @author Hyc
 *
 */
public class RobotChatServiceTest extends BaseTest{

    @Autowired
    private RobotChatService rcs;


    /**
     * Test method for {@link com.pentaKill.service.RobotChatService#getRobotFlagService(int)}.
     */
    @Test
    public void testGetRobotFlagService() {
        int company_id = 1;
        int flag = rcs.getRobotFlagService(company_id);
        Assert.assertEquals("得到RobotFlag失败", 1, flag);
        
    }

    /**
     * Test method for {@link com.pentaKill.service.RobotChatService#getRobotQuestionService(int)}.
     */
    @Test
    public void testGetRobotQuestionService() {
        int company_id = 1;
        List<RobotQuestionBean> temp = rcs.getRobotQuestionService(company_id);
        Assert.assertEquals("得到RobotQuestion失败", false, temp.isEmpty());
    }

}
