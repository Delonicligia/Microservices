package com.delonic.buku_service.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.delonic.buku_service.repository.read", // Repository Read
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager"
)
public class QueryDBConfig {

    @Bean(name = "readDataSource")
    public DataSource readDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:buku_read_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean(name = "readEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("readDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.delonic.buku_service.model") // entity
                .persistenceUnit("read")
                .build();
    }

    @Bean(name = "readTransactionManager")
    public JpaTransactionManager readTransactionManager(
            @Qualifier("readEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
