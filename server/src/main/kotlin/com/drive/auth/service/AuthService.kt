package com.drive.auth.service

import com.drive.auth.config.JwtService
import com.drive.auth.dao.AuthRes
import com.drive.auth.dao.NewUserDTO
import com.drive.auth.dao.UserDTO
import com.drive.auth.model.Roles
import com.drive.auth.model.User
import com.drive.exception.customexceptions.NotAvailableException
import com.drive.auth.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.math.log

@Service
class AuthService @Autowired constructor(
    private val userRepo: UserRepo,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager

) {

    fun signup(userDTO: NewUserDTO): AuthRes {
        val isUsernameExit = userRepo.findAllByUsernameEquals(userDTO.username)
        if (isUsernameExit.isPresent) throw NotAvailableException(HttpStatus.CONFLICT, "Username already exits")

        val isEmailExit = userRepo.findAllByEmailEquals(userDTO.email)
        if (isUsernameExit.isPresent) throw NotAvailableException(HttpStatus.CONFLICT, "Email already exits")

        //encrypt
        val encryptPass = passwordEncoder.encode(userDTO.password)

        val user = User(username = userDTO.username, email = userDTO.email, password = encryptPass, role = Roles.ADMIN)

        val dbUser = userRepo.save(user);
        println(dbUser)

        val jwtToken = jwtService.generateToken(dbUser);

        return AuthRes(dbUser.username, dbUser.email, jwtToken, LocalDateTime.now())

    }

    fun login(userDTO: UserDTO): AuthRes {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userDTO.username, userDTO.password))

        val dbUser = userRepo.findAllByUsernameEquals(userDTO.username)
            .orElseThrow { UsernameNotFoundException("User Not Found") }

        val jwtToken = jwtService.generateToken(dbUser);

        return AuthRes(dbUser.username, dbUser.email, jwtToken, LocalDateTime.now())
    }

}