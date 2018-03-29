package com.pentaKill.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.RobotMapper;
import com.pentaKill.domain.RobotKnowledge;
import com.pentaKill.domain.UpdateKnowledgeBean;

@Service
@Scope
public class RobotService {
	
	@Resource
	private RobotMapper robotMapper;
	
	public int getRobotId(int company_id){
		int robot_id = robotMapper.getRobotId(company_id);
		return robot_id;
	}
	
	public void insertKnowledge(RobotKnowledge robotKnowledge){
		robotMapper.inserKnowledge(robotKnowledge);
	}
	
	public List<RobotKnowledge> getRobotAllKnowledge(int robot_id){
		List<RobotKnowledge> list = robotMapper.getAllRobotKnowledge(robot_id);
		return list;
	}
	
	public void updateKnowledge(UpdateKnowledgeBean updateKnowledgeBean){
		robotMapper.updateKnowledge(updateKnowledgeBean);
	}
	
	public void deleteKnowledge(int knowledge_id){
		robotMapper.deleteKnowledge(knowledge_id);
	}
}
