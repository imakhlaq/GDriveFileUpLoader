package com.drive.auth.repo


import com.drive.auth.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserRepo : JpaRepository<User, UUID> {
    fun findAllByUsernameEquals(username: String): Optional<User>
    fun findAllByEmailEquals(email: String): Optional<User>
}