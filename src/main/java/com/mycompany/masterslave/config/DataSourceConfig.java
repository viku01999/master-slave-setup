package com.mycompany.masterslave.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/*
 * This configuration creates:
 *
 * 1. Master DataSource
 * 2. Slave DataSource
 * 3. RoutingDataSource (Primary)
 *
 * Spring will use routingDataSource automatically.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    /*
     * Binds properties:
     * spring.datasource.master.*
     */
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    /*
     * Creates MASTER datasource using above properties
     */
    @Bean
    public DataSource masterDataSource() {
        return masterDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    /*
     * Binds properties:
     * spring.datasource.slave.*
     */
    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    /*
     * Creates SLAVE datasource
     */
    @Bean
    public DataSource slaveDataSource() {
        return slaveDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    /*
     * Routing datasource (Primary)
     */
    @Bean
    @Primary
    public DataSource routingDataSource() {

        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DataSourceType.MASTER, masterDataSource());
        dataSources.put(DataSourceType.SLAVE, slaveDataSource());

        routingDataSource.setTargetDataSources(dataSources);
        routingDataSource.setDefaultTargetDataSource(masterDataSource());

        return routingDataSource;
    }
}