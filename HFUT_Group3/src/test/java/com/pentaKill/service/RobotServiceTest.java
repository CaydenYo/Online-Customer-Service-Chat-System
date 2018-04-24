/**
 * Hyc
 *2018年4月20日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.RobotKnowledge;
import com.pentaKill.domain.RobotStatusBean;
import com.pentaKill.domain.UpdateKnowledgeBean;

/**
 * @author Hyc
 *
 */
public class RobotServiceTest extends BaseTest{

    @Autowired
    private RobotService rs;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link com.pentaKill.service.RobotService#getRobotId(int)}.
     */
    @Test
    public void testGetRobotId() {
        int company_id = 1;
        int robot_id = rs.getRobotId(company_id);
        Assert.assertEquals("得到Robot_id失败", 0, robot_id);
        
    }

    /**
     * Test method for {@link com.pentaKill.service.RobotService#insertKnowledge(com.pentaKill.domain.RobotKnowledge)}.
     */
    @Test
    public void testInsertKnowledge() {
        int robot_id = 0;
        RobotKnowledge robotKnowledge = new RobotKnowledge(5, robot_id, "你是谁", "XIAO AI");
        rs.insertKnowledge(robotKnowledge);
        List<RobotKnowledge> list = rs.getRobotAllKnowledge(robot_id);
        for(RobotKnowledge rk:list){
            System.out.println(rk);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.RobotService#getRobotAllKnowledge(int)}.
     */
    @Test
    public void testGetRobotAllKnowledge() {
        int robot_id = 0;
        List<RobotKnowledge> list = rs.getRobotAllKnowledge(robot_id);
        for(RobotKnowledge rk:list){
            System.out.println(rk);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.RobotService#updateKnowledge(com.pentaKill.domain.UpdateKnowledgeBean)}.
     */
    @Test
    public void testUpdateKnowledge() {
        UpdateKnowledgeBean updateKnowledgeBean = new UpdateKnowledgeBean();
        updateKnowledgeBean.setAnswer("hahaha");
        updateKnowledgeBean.setKnowledge_id(5);
        updateKnowledgeBean.setQuestion("e");
        rs.updateKnowledge(updateKnowledgeBean);
        int robot_id = 0;
        List<RobotKnowledge> list = rs.getRobotAllKnowledge(robot_id);
        for(RobotKnowledge rk:list){
            System.out.println(rk);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.RobotService#deleteKnowledge(int)}.
     */
    @Test
    public void testDeleteKnowledge() {
        int knowledge_id = 2;
        rs.deleteKnowledge(knowledge_id);
        int robot_id = 0;
        List<RobotKnowledge> list = rs.getRobotAllKnowledge(robot_id);
        for(RobotKnowledge rk:list){
            System.out.println(rk);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.RobotService#setRobotStatus(com.pentaKill.domain.RobotStatusBean)}.
     */
    @Test
    public void testSetRobotStatus() {
        RobotStatusBean rsb = new RobotStatusBean();
        rsb.setCompany_id(1);
        rsb.setRobot_open_flag(1);
        rs.setRobotStatus(rsb);
    }

}
