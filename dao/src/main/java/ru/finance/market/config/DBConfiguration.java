package ru.finance.market.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import lombok.var;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.postgresql.PGProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

import static java.util.concurrent.TimeUnit.SECONDS;
import static net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel.*;

@Configuration
@EnableConfigurationProperties({DBProperties.class, LiquibaseProperties.class})
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(value = "ru.finance.market")
@EntityScan(basePackages = "ru.finance.market")
public class DBConfiguration {

    public static final String SQL_LOGGER = "ru.finance.market.dao.market.ProxyDataSource";

    @Bean
    @Primary
    DataSource dataSource(DBProperties dbProps) {
        return createDataSource(dbProps);
    }

    private DataSource createDataSource(DBProperties dbProps) {
        HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder.create()
                .url(dbProps.getUrl())
                .driverClassName(dbProps.getDriver())
                .username(dbProps.getUser())
                .password(dbProps.getPassword())
                .build();
        dataSource.setAutoCommit(false);
        dataSource.setRegisterMbeans(true);
        dataSource.setPoolName("application-pool");
        dataSource.setMinimumIdle(0);
        dataSource.addDataSourceProperty(PGProperty.REWRITE_BATCHED_INSERTS.getName(), true);
        return ProxyDataSourceBuilder.create(dataSource)
                .countQuery()
                .logQueryBySlf4j(TRACE, SQL_LOGGER)
                .logSlowQueryBySlf4j(30, SECONDS, WARN, SQL_LOGGER)
                .logSlowQueryBySlf4j(60, SECONDS, ERROR, SQL_LOGGER)
                .multiline()
                .build();
    }

    @Bean("liquibaseDs")
    DataSource liquibaseDataSource(DBProperties dbProps) {
        return DataSourceBuilder.create()
                .url(dbProps.getUrl())
                .driverClassName(dbProps.getDriver())
                .username(dbProps.getMigratorUser())
                .password(dbProps.getMigratorPassword())
                .build();
    }

    @Bean
    @ConfigurationProperties("spring.liquibase")
    SpringLiquibase liquibase(LiquibaseProperties props, @Qualifier("liquibaseDs") DataSource dataSource) {
        var liquibase = new SpringLiquibase();
        liquibase.setChangeLog(props.getChangeLog());
        liquibase.setDefaultSchema(props.getDefaultSchema());
        liquibase.setTestRollbackOnUpdate(props.isTestRollbackOnUpdate());
        liquibase.setDropFirst(props.isDropFirst());
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    @Bean
    @Primary
    TransactionTemplate txTemplate(PlatformTransactionManager txManager, DBProperties props) {
        var txTemplate = new TransactionTemplate(txManager);
        txTemplate.setTimeout((int) props.getDefaultTxTimeout().getSeconds());
        return txTemplate;
    }
}
