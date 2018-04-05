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

    public List<FastReplyBean> showFastReplyService(int cs_id) {
        List<FastReplyBean> temp = fastReplyMapper.showFastReplyMapper(cs_id);
        return temp;
    }

    public void addFastReplyService(FastReplyBean frb) {
        fastReplyMapper.addFastReplyMapper(frb);
    }

    public void deleteFastReplyService(FastReplyBean frb) {
        fastReplyMapper.deleteFastReplyMapper(frb);
    }

    public void modifyFastReplyService(FastReplyBean frb) {
        fastReplyMapper.modifyFastReplyMapper(frb);
    }
}
