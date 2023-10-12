package com.drive.auth

import com.drive.auth.dao.AuthRes
import com.drive.auth.dao.NewUserDTO
import com.drive.auth.dao.UserDTO
import com.drive.auth.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController @Autowired constructor(private val authService: AuthService) {

    @PostMapping("/signup")
    fun signup(@RequestBody userDTO: NewUserDTO): ResponseEntity<AuthRes> {
        println("IN signup")

        val data = authService.signup(userDTO);
        println(data)

        return ResponseEntity.ok(data);
    }

    @PostMapping("/login")
    fun login(@RequestBody userDTO: UserDTO): ResponseEntity<AuthRes> {
        println("IN login")
        return ResponseEntity.ok(this.authService.login(userDTO));
    }
}