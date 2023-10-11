package com.drive.auth.config


import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter @Autowired constructor(val jwtService: JwtService, val userDetailsService: UserDetailsService) :
    OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token: String? = request.getHeader("Authorization")
        println("Inside filter $token")

        val startWith = token?.startsWith("Bearer ")

        if (token == null || startWith == false) {
            println("32 Line")
            //call next filter
            filterChain.doFilter(request, response);
            return;
        }
        //extract the token

        val jwtToken = token.split(" ")[1];

        val username = jwtService.extractUserName(jwtToken);

        if (username !== null && SecurityContextHolder.getContext().authentication == null) {

            val userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request);
                SecurityContextHolder.getContext().authentication = authToken;
            }
        }

        filterChain.doFilter(request, response)
    }
}