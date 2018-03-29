package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.RobotKnowledge;
import com.pentaKill.domain.UpdateKnowledgeBean;

public interface RobotMapper {

	public int getRobotId(int company_id);

	public void inserKnowledge(RobotKnowledge robotKnowledge);

	public List<RobotKnowledge> getAllRobotKnowledge(int robot_id);

	public void updateKnowledge(UpdateKnowledgeBean updateKnowledgeBean);
	
	public void deleteKnowledge(int knowledge_id);
	
}
