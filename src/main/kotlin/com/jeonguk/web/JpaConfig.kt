package com.jeonguk.web

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.jeonguk.web.repository"])
class JpaConfig {
}