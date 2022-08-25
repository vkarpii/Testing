package com.webapp.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DataSource gives out connections from Hikari Pool
 */
@Slf4j
public class DataSource {
    private static HikariDataSource dataSource;
    private static final String PROPERTIES_NAME = "application.properties";

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        if (dataSource == null)
            try {
                Properties properties = new Properties();
                InputStream inputStream = DataSource.class
                        .getClassLoader()
                        .getResourceAsStream(PROPERTIES_NAME);
                properties.load(inputStream);
                HikariConfig config = new HikariConfig(properties);
                dataSource = new HikariDataSource(config);
            } catch (IOException e){
               log.error("Error : {}",e.getMessage());
            }
        return dataSource.getConnection();
    }
}
