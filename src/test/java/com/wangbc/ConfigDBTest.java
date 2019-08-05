package com.wangbc;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 15201
 * \* Date: 2019/7/29
 * \* Time: 17:03
 * \* Description:
 * \
 */
public class ConfigDBTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDBTest.class);


    @Test
    public void testConfig1(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();

        LOGGER.info("configuration = {}",configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        LOGGER.info("获取流程引擎 {}",processEngine.getName());
        processEngine.close();
    }

    @Test
    public void testConfig2(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_druid.cfg.xml");

        LOGGER.info("configuration = {}",configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        LOGGER.info("获取流程引擎 {}",processEngine.getName());
        processEngine.close();
    }




}
