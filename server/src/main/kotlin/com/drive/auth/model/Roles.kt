package com.drive.auth.model

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.Collections;
import java.util.stream.Collectors


enum class Role(private val permission: MutableSet<Permission>) {
    USER(Collections.emptySet()),
    ADMIN(
        mutableSetOf(
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_CREATE,
            Permission.MANAGER_READ,
            Permission.MANAGER_UPDATE,
            Permission.MANAGER_DELETE,
            Permission.MANAGER_CREATE
        )
    ),
    MANAGER(
        mutableSetOf(
            Permission.MANAGER_READ,
            Permission.MANAGER_UPDATE,
            Permission.MANAGER_DELETE,
            Permission.MANAGER_CREATE
        )
    );


    fun getAuthorities(): MutableList<SimpleGrantedAuthority> {
        val authorities = permission
            .stream()
            .map { permission -> SimpleGrantedAuthority(permission.name) }
            .collect(Collectors.toList())
        authorities?.add(SimpleGrantedAuthority("ROLE_$name"))
        return authorities;
    }
}