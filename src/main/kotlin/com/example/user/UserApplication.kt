package com.example.user

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class UserApplication {

    @Bean
    fun commandLineRunner(jdbcTemplate: JdbcTemplate) = CommandLineRunner {
        jdbcTemplate.execute("""CREATE TABLE IF NOT EXISTS task (
            id          BIGINT          PRIMARY KEY AUTO_INCREMENT,
            email       VARCHAR(100)    NOT NULL UNIQUE,
            password    VARCHAR(20)     NOT NULL
        )""".trimIndent())
    }
}

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
