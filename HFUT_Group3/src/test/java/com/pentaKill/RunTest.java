package com.pentaKill;
/**
 * Hyc
 *2018年4月21日
 */

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Suite;

import com.pentaKill.service.*;
import com.pentaKill.util.EmailUtilTest;



@RunWith(Suite.class)

@Suite.SuiteClasses({CompanyServiceTest.class, ConversationServiceTest.class,
    CSViewsCustomerInfoServiceTest.class,
    CustomerLoginServiceTest.class,CustomerRegisterServiceTest.class,
    CustomerServiceMServiceTest.class,EmailUtilTest.class,
    SessionTransferServiceTest.class,RobotChatServiceTest.class,})
/**
 * @author Hyc
 *
 */
public class RunTest {

//没有任何的逻辑代码，为空​

}


