package com.jeonguk.web.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.jeonguk.web.repository"],
    entityManagerFactoryRef = "entityManager",
    transactionManagerRef = "transactionManager"
)
class JpaConfig(private val env: Environment) {

    @Bean
    fun entityManager(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter = HibernateJpaVendorAdapter().apply {
            setDatabase(Database.H2)
            setGenerateDdl(true)
        }
        return LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSource()
            setPackagesToScan("com.jeonguk.web.domain.entity")
            jpaVendorAdapter = vendorAdapter
            setJpaProperties(additionalProperties())
        }
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager().apply {
            entityManagerFactory = emf
        }
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    private fun additionalProperties(): Properties {
        return Properties().apply {
            setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"))
            setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"))
            setProperty(
                "hibernate.current_session_context_class",
                env.getProperty("spring.jpa.properties.hibernate.current_session_context_class")
            )
            setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"))
            setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"))
        }
    }

}
