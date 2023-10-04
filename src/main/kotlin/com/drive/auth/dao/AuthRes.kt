package com.drive.auth.dao

import java.time.LocalDateTime

data class AuthRes(val username: String, val email: String, val token: String, val timestamp: LocalDateTime)
