package com.drive.model

import com.drive.auth.model.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.api.client.util.DateTime
import jakarta.persistence.*
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.ToString
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Data
class StoredFiles(
    val fileName: String?,
    val url: String?,
    val contentType: String?,
    val timestamp: DateTime?,
    val size: Long,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    val user: User? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
)
