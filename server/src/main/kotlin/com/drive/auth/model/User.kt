package com.drive.auth.model

import com.drive.model.StoredFiles
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.ToString
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Data
@Table(name = "user_details")
class User(

    @Column(unique = true)
    private val username: String,

    @Column(unique = true)
    val email: String,

    private val password: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    @Column(nullable = true)
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    @JsonIgnoreProperties("files")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    val files: List<StoredFiles>? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    val id: UUID? = null,

    ) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return role.getAuthorities();
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