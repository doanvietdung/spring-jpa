package com.example.learnspring.infrastructure.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryNewBag",
        transactionManagerRef = "newBagTransactionManager",
        basePackages = {"com.example.learnspring.infrastructure.repositories.new_bag"})
public class NewBagDatasourceConfig {
    public static final String PERSISTENCE_UNIT_NAME = "new-bag";
    public static final String MODEL_PACKAGE = "com.example.learnspring.infrastructure.repositories.new_bag.entity";

    @Bean(name = "newBagDataSource")
    @ConfigurationProperties("spring.datasource-new-bag")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryNewBag")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryNewBag(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("newBagDataSource") DataSource dataSource) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages(MODEL_PACKAGE)
                .persistenceUnit(PERSISTENCE_UNIT_NAME)
                .build();
    }

    @Bean(name = "newBagTransactionManager")
    public PlatformTransactionManager newBagTransactionManager(
            @Qualifier("entityManagerFactoryNewBag") EntityManagerFactory dfEntityManagerFactory) {
        return new JpaTransactionManager(dfEntityManagerFactory);
    }
}
