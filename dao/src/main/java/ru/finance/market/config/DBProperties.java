package ru.finance.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "db")
public class DBProperties {
    private String url;
    private int port;

    private String schema;

    private String user;
    private String password;

    private String migratorUser;
    private String migratorPassword;

    private String driver;

    private Duration defaultTxTimeout;
}