package com.drive.model

import com.drive.auth.model.User
import com.google.api.client.util.DateTime
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class StoredFiles(
    val fileName: String?,
    val url: String?,
    val contentType: String?,
    val timestamp: DateTime?,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    val user: User? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
)
