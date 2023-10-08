package com.drive.model

import com.drive.auth.model.User
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class StoredFiles(
    val fileName: String,
    val url: String,
    val contentType: String,
    val timestamp: LocalDateTime,
    @Column(nullable = true)
    @ManyToOne
    val user: User? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
)
