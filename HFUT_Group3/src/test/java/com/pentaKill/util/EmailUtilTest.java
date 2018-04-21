/**
 * Hyc
 *2018年4月12日
 */
package com.pentaKill.util;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.pentaKill.utils.EmailUtil;

/**
 * @author Hyc
 *
 */
public class EmailUtilTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link com.pentaKill.utils.EmailUtil#sendTo(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testSendto() {
        String code = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        code = code.replaceAll("-", "");
        try {
            StringBuffer sbd = new StringBuffer();
            sbd.append("<br/>欢迎！请确认此邮件地址以激活您的账号。<br/>");
            sbd.append("<font color='red'><a href='http://127.0.0.1/OCSSystem/regconf.do?code="
                    + code + "' target='_blank'");
            sbd.append(">立即激活</a></font><br/>");
            sbd.append("或者点击下面链接:<br/>");
            sbd.append("http://127.0.0.1/OCSSystem/regconf.do?code=" + code
                    + "<br/>");
            sbd.append("这是一封自动发送的邮件；如果您并未要求但收到这封信件，您不需要进行任何操作。");

            EmailUtil.sendTo(sbd.toString(), "841160480@qq.com");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
