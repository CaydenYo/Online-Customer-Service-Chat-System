package com.pentaKill;






import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.pentaKill.dao.CustomerServiceMapper;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
//@ContextConfiguration({ "classpath*:src/webapp/WEB-INF/ApplicationContext.xml", "classpath*:src/webapp/WEB-INF/SpringDispatcher-servlet.xml" })
@ContextConfiguration({ "classpath*:spring/spring-dao.xml", "classpath*:spring/spring-service.xml" })
@SuppressWarnings("deprecation")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BaseTest {
    @Autowired
    private CustomerServiceMapper csm;
    
    @Test
    public void testInfo(){
    
    Assert.assertEquals("计算后缀有问题", 1, 1); 
    }
}
