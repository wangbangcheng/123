package com.wangbc;

import com.wangbc.event.MyEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 15201
 * \* Date: 2019/7/31
 * \* Time: 19:49
 * \* Description:  MDC测试
 * \
 */
public class ConfigJobTest {
    private static final Logger LOGGER= LoggerFactory.getLogger(ConfigJobTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_job.cfg.xml");

    @Test
    @Deployment(resources = {"com/wangbc/my-process_job.bpmn20.xml"})
    public void test() throws InterruptedException {
        LOGGER.info("start");
        List<Job> jobList = activitiRule
                .getManagementService()
                .createTimerJobQuery()
                .listPage(0, 100);

        for (Job job:jobList){
            LOGGER.info("定时任务 = {}  ， 默认重试次数 = {} ",job , job.getRetries());
        }
        LOGGER.info("定时任务数量 = {}",jobList.size());
       Thread.sleep(1000*100);
        LOGGER.info("end");


    }


}
