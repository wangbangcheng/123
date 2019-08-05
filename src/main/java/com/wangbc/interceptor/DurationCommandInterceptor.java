package com.wangbc.interceptor;

import com.wangbc.event.MyEventListener;
import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \*
 * \* User: 15201
 * \* Date: 2019/8/3
 * \* Time: 17:45
 * \* Description:
 * \
 */
public class DurationCommandInterceptor extends AbstractCommandInterceptor {
    private static final Logger LOGGER =  LoggerFactory.getLogger(DurationCommandInterceptor.class);

    public <T> T execute(CommandConfig commandConfig, Command<T> command) {
        long start = System.currentTimeMillis();
        try {
            return this.getNext().execute(commandConfig,command);
        }finally {
            long duration = System.currentTimeMillis() - start;
            LOGGER.info("{} 执行时长 {} 毫秒",command.getClass().getSimpleName(),duration);
        }
    }
}
