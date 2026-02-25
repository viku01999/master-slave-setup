package com.mycompany.masterslave.config;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*
 * This Aspect runs BEFORE any Service method.
 * AOP (Automatic Switching)
 * It checks:
 * If @Transactional(readOnly = true)
 *      → use SLAVE
 * Else
 *      → use MASTER
 *
 * After method completes → clear ThreadLocal
 */
@Aspect
@Component
public class DataSourceRoutingAspect {

    @Before("@annotation(transactional)")
    public void setDataSource(Transactional transactional) {

        if (transactional.readOnly()) {
            DataSourceContextHolder.set(DataSourceType.SLAVE);
        } else {
            DataSourceContextHolder.set(DataSourceType.MASTER);
        }
    }

    @After("@annotation(transactional)")
    public void clearDataSource(Transactional transactional) {
        DataSourceContextHolder.clear();
    }
}
