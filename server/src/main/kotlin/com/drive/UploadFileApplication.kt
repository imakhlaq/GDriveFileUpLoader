package com.drive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
class UploadFileApplication

fun main(args: Array<String>) {
    runApplication<UploadFileApplication>(*args)
}
