package com.jeonguk.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
        scanBasePackages = ["com.jeonguk.web"],
        exclude = [
            DataSourceAutoConfiguration::class,
            DataSourceTransactionManagerAutoConfiguration::class,
            HibernateJpaAutoConfiguration::class]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
