package com.pentaKill.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.FastReplyMapper;
import com.pentaKill.domain.FastReplyBean;

@Service
@Scope
public class FastReplyService {
    @Resource
    FastReplyMapper fastReplyMapper;

    public List<FastReplyBean> showFastReply_service(int cs_id) {
        List<FastReplyBean> temp = fastReplyMapper.showFastReply_mapper(cs_id);
        return temp;
    }

    public void addFastReply_service(FastReplyBean frb) {
        fastReplyMapper.addFastReply_mapper(frb);
    }

    public void deleteFastReply_service(FastReplyBean frb) {
        fastReplyMapper.deleteFastReply_mapper(frb);
    }

    public void modifyFastReply_service(FastReplyBean frb) {
        fastReplyMapper.modifyFastReply_mapper(frb);
    }
}
