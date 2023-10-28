package com.drive.auth.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
internal class SecurityConfig {
    @Autowired
    var jwtAuthFilter: JwtAuthFilter? = null

    @Autowired
    var authenticationProvider: AuthenticationProvider? = null

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf -> csrf.disable() }
            .cors { cors ->
                cors.configurationSource { request ->
                    val corsConfig = CorsConfiguration()
                    corsConfig.allowedOrigins = listOf("http://localhost:3000", "http://127.0.0.1:3000")
                    corsConfig.setAllowedMethods(listOf("GET", "POST", "PUT", "DELETE", "OPTIONS"))
                    corsConfig.allowedHeaders = listOf("*")
                    corsConfig
                }
            }.formLogin { obj -> obj.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/api/auth/**") //these are whitelist paths
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }//and rest are private need auth
            .sessionManagement { session ->
                session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(
                jwtAuthFilter,
                UsernamePasswordAuthenticationFilter::class.java
            ) //run this before auth filter
        return http.build()
    }

    // To enable CORS
    //    @Bean //==> auth detects if you set the cors(withDefaults())
    //    public CorsConfigurationSource corsConfigurationSource() {
    //        System.out.println("here");
    //        CorsConfiguration configuration = new CorsConfiguration();
    //        configuration.setAllowedOrigins(Arrays.asList("*"));
    //        configuration.setAllowedMethods(Arrays.asList("*"));
    //        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //        source.registerCorsConfiguration("/**", configuration);
    //        return source;
    //    }
}
