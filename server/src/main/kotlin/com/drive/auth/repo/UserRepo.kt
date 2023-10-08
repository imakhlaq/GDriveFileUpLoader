package com.drive.auth.repo


import com.drive.auth.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepo : JpaRepository<User, UUID> {
    fun findAllByUsernameEquals(username: String): Optional<User>
    fun findAllByEmailEquals(email: String): Optional<User>
}