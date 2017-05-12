package com.beforever.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by BeForever on 17/5/12.
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(*.com.beforever.controller.IndexController.*(..))")
    public void beforeMethod() {
        logger.info("before method");
    }

    @After("execution(*.com.beforever.controller.IndexController.*(..))")
    public void afterMethod() {
        logger.info("after  method");
    }
}
