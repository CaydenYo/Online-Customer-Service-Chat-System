/**
 * Hyc
 *2018年4月23日
 */
package com.pentaKill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pentaKill.BaseTest;
import com.pentaKill.domain.FastReplyBean;

/**
 * @author Hyc
 *
 */
public class FastReplyServiceTest extends BaseTest{

    @Autowired
    private FastReplyService frs;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link com.pentaKill.service.FastReplyService#showFastReplyService(int)}.
     */
    @Test
    public void testShowFastReplyService() {
        int cs_id = 1000;
        List<FastReplyBean> list = frs.showFastReplyService(cs_id);
        for(FastReplyBean frb:list){
            System.out.println(frb);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.FastReplyService#addFastReplyService(com.pentaKill.domain.FastReplyBean)}.
     */
    @Test
    public void testAddFastReplyService() {
        FastReplyBean frb = new FastReplyBean();
        frb.setContent("aaa");
        frb.setCs_id(1000);
        frb.setShortcut_language_id(5);
        frs.addFastReplyService(frb);
        List<FastReplyBean> list = frs.showFastReplyService(1000);
        for(FastReplyBean f:list){
            System.out.println(f);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.FastReplyService#deleteFastReplyService(int)}.
     */
    @Test
    public void testDeleteFastReplyService() {
        int shortcut_language_id = 2;
        frs.deleteFastReplyService(shortcut_language_id);
        List<FastReplyBean> list = frs.showFastReplyService(1000);
        for(FastReplyBean f:list){
            System.out.println(f);
        }
    }

    /**
     * Test method for {@link com.pentaKill.service.FastReplyService#modifyFastReplyService(int, java.lang.String)}.
     */
    @Test
    public void testModifyFastReplyService() {
        frs.modifyFastReplyService(1,"aaabbb");
        List<FastReplyBean> list = frs.showFastReplyService(1000);
        for(FastReplyBean f:list){
            System.out.println(f);
        }
    }

}
