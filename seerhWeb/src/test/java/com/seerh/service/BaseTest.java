package com.seerh.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"/applicationContext-configuration.xml",
								 "/applicationContext-mybatis.xml", 
								 "/applicationContext-shiro.xml", 
								 "/mybatis-config.xml"})  
public class BaseTest {

}
