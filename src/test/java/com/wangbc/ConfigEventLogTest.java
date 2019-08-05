package com.wangbc;

import com.wangbc.event.MyEventListener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 15201
 * \* Date: 2019/7/31
 * \* Time: 19:49
 * \* Description:  MDC测试
 * \
 */
public class ConfigEventLogTest {
    private static final Logger LOGGER= LoggerFactory.getLogger(ConfigEventLogTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_event.cfg.xml");

    @Test
    @Deployment(resources = {"com/wangbc/my-process.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());

        activitiRule.getRuntimeService().addEventListener(new MyEventListener());
        activitiRule.getRuntimeService().dispatchEvent(new ActivitiEventImpl(ActivitiEventType.CUSTOM));
        //将捕捉事件全部打出
//        List<EventLogEntry> eventLogEntries = activitiRule.getManagementService()
//                .getEventLogEntriesByProcessInstanceId(processInstance.getProcessInstanceId());
//
//        for (EventLogEntry eventLogEntry:eventLogEntries){
//            LOGGER.info("eventLogEntry = {} ",eventLogEntry);
//        }
//        LOGGER.info("eventLogEntries = {} ",eventLogEntries.size());


    }


}
