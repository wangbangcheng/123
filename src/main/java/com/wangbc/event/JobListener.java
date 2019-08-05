package com.wangbc.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \*
 * \* User: 15201
 * \* Date: 2019/8/3
 * \* Time: 15:56
 * \* Description:
 * \
 */
public class JobListener implements ActivitiEventListener {

    private static final Logger LOGGER =  LoggerFactory.getLogger(JobListener.class);

    public void onEvent(ActivitiEvent activitiEvent) {
        ActivitiEventType eventType = activitiEvent.getType();
        String name = eventType.name();
        if(name.startsWith("TIMER")|| name.startsWith("JOB")){
            LOGGER.info("监听到JOB事件 {}",name);
    }

    }

    public boolean isFailOnException() {
        return false;
    }
}
