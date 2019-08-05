package com.wangbc.delegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \* 描述：
 * \*        ConfigSpring 测试
 * \*
 * \* @auther:    15201
 * \* @date:      2019/8/5 14:48
 * \
 */
public class HelloBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(HelloBean.class);
    public void sayHello(){
        LOGGER.info("sayHello!");
    }
}
