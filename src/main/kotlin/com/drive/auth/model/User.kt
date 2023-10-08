package com.drive.auth.model

import com.drive.model.StoredFiles
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table(name = "user_details")
data class User(

    @Column(unique = true)
    private val username: String,

    @Column(unique = true)
    val email: String,

    private val password: String,

    @Enumerated(EnumType.STRING)
    val role: Roles,

    @OneToMany(cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY)
    @Column(nullable = true)
    val files: List<StoredFiles>? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    ) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
        return true;
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun isEnabled(): Boolean {
        return true;
    }
}