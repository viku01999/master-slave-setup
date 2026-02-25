package com.mycompany.masterslave.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/*
 * This class decides which actual datasource to use.
 *
 * Spring calls determineCurrentLookupKey()
 * before executing a DB operation.
 *
 * It returns MASTER or SLAVE based on ThreadLocal value.
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}