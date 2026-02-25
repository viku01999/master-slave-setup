package com.mycompany.masterslave.config;


/*
 * ThreadLocal stores datasource type per request thread.
 *
 * Each HTTP request runs in separate thread.
 * So we store MASTER or SLAVE for that request only.
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void set(DataSourceType type) {
        contextHolder.set(type);
    }

    public static DataSourceType get() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}
