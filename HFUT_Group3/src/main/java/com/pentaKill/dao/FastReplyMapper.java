package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.FastReplyBean;

public interface FastReplyMapper {
    public List<FastReplyBean> showFastReplyMapper(int cs_id);

    public void addFastReplyMapper(FastReplyBean frb);

    public void deleteFastReplyMapper(int shortcut_language_id);

    public void modifyFastReplyMapper(int shortcut_language_id,String content);

}
