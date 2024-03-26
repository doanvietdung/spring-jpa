package com.example.learnspring.infrastructure.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
@Getter
public class HikariCPDataSource {
    private final HikariDataSource ds;


    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public HikariCPDataSource(String jdbcUrl, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionTimeout(35000);
        ds = new HikariDataSource(config);
    }

    public HikariCPDataSource(String jdbcUrl, String username, String password, int maxPoolSize) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionTimeout(35000);
        config.setMaximumPoolSize(maxPoolSize);
        ds = new HikariDataSource(config);
    }
}
