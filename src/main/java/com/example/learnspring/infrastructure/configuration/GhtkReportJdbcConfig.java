package com.example.learnspring.infrastructure.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.learnspring.infrastructure.repositories.ecom.report",
        entityManagerFactoryRef = "entityManagerFactoryEcomReport",
        transactionManagerRef = "transactionManagerEcom")
public class GhtkReportJdbcConfig {
    @Value(value = "${jdbc.ghtk.url}")
    private String jdbcGhtkUrl;

    @Value(value = "${jdbc.ghtk.user}")
    private String jdbcGhtkUser;


    @Value(value = "${jdbc.ghtk.password}")
    private String jdbcGhtkPassword;

    @Qualifier("ecom_report")
    @Bean(name = "ecom")
    public HikariDataSource ecom() {
        HikariCPDataSource config = new HikariCPDataSource(jdbcGhtkUrl, jdbcGhtkUser, jdbcGhtkPassword, 2);
        return config.getDs();
    }

    @Bean(name = "jdbcTemplateEcomReport")
    public JdbcTemplate jdbcTemplateEcomReport(@Qualifier("ecom") HikariDataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "entityManagerFactoryEcomReport")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryEcomReport(@Qualifier("ecom") HikariDataSource ds) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds);
        em.setPackagesToScan("com.example.learnspring.infrastructure.repositories.ecom.report.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        em.setJpaProperties(properties);

        return em;
    }

    @Bean(name = "transactionManagerEcom")
    public PlatformTransactionManager transactionManagerEcom(@Qualifier("entityManagerFactoryEcomReport") EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }
}
