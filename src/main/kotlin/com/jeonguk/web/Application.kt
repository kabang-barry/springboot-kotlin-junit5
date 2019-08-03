package com.jeonguk.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.jeonguk.web"], exclude = [SecurityAutoConfiguration::class])
class SpringbootKotlinJunit5Application

fun main(args: Array<String>) {
	runApplication<SpringbootKotlinJunit5Application>(*args)
}
