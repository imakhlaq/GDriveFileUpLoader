package com.drive.exception

import org.springframework.http.HttpStatusCode
import java.time.LocalDateTime

data class ErrorPOJO(val statusCode: HttpStatusCode, val message: String, val timestamp: LocalDateTime)
