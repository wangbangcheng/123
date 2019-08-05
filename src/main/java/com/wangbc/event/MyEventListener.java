package com.wangbc.event;

import com.wangbc.delegate.MDCErrorDelegate;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

/**
 * \*
 * \* User: 15201
 * \* Date: 2019/8/3
 * \* Time: 15:56
 * \* Description:
 * \
 */
public class MyEventListener implements ActivitiEventListener {

    private static final Logger LOGGER =  LoggerFactory.getLogger(MyEventListener.class);

    public void onEvent(ActivitiEvent activitiEvent) {
        ActivitiEventType eventType = activitiEvent.getType();
        switch (eventType){
            case PROCESS_STARTED:
                LOGGER.info("流程启动 {} / {}",eventType ,activitiEvent.getProcessInstanceId());
                break;
            case PROCESS_COMPLETED:
                LOGGER.info("流程完成 {} / {}" , eventType,activitiEvent.getProcessInstanceId());
                break;
            case CUSTOM:
                LOGGER.info("监听到自定义事件 {} / {}",eventType,activitiEvent.getProcessInstanceId());
                break;
        }

    }

    public boolean isFailOnException() {
        return false;
    }
}
