package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.FastReplyBean;

public interface FastReplyMapper {
    public List<FastReplyBean> showFastReplyMapper(int cs_id);

    public void addFastReplyMapper(FastReplyBean frb);

    public void deleteFastReplyMapper(FastReplyBean frb);

    public void modifyFastReplyMapper(FastReplyBean frb);

}
