package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.FastReplyBean;

public interface FastReplyMapper {
    public List<FastReplyBean> showFastReply_mapper(int cs_id);

    public void addFastReply_mapper(FastReplyBean frb);

    public void deleteFastReply_mapper(FastReplyBean frb);

    public void modifyFastReply_mapper(FastReplyBean frb);

}
