package com.mazyde.cargo.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.ZonedDateTime;
import java.util.Optional;

@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware", dateTimeProviderRef = "auditingDateTimeProvider")
@EnableJpaRepositories(
    transactionManagerRef = "transactionManager",
    entityManagerFactoryRef = "entityManagerFactory",
    value = "com.mazyde", includeFilters = @ComponentScan.Filter(pattern = "..*JpaRepository", type = FilterType.REGEX)
)
@EntityScan("com.journal")
@Configuration
public class PersistenceContext {

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }
}
