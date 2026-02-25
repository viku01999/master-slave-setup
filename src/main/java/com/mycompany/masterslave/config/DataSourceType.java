package com.mycompany.masterslave.config;



/*
 * This enum represents which database should be used.
 *
 * MASTER → used for write operations (INSERT, UPDATE, DELETE)
 * SLAVE  → used for read operations (SELECT)
 */
public enum DataSourceType {
    MASTER,
    SLAVE
}
