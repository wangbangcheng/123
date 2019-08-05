package com.wangbc.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 15201
 * \* Date: 2019/7/31
 * \* Time: 20:48
 * \* Description:
 * \
 */
public class MDCErrorDelegate implements JavaDelegate {
    private static final Logger LOGGER =  LoggerFactory.getLogger(MDCErrorDelegate.class);


    public void execute(DelegateExecution delegateExecution) {
        LOGGER.info("run MDCErrorDelegate");
        throw new RuntimeException("only MDCErrorDelegate test");
    }
}
