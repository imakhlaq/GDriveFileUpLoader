package com.drive.gdrive.repo

import com.drive.auth.model.User
import com.drive.model.StoredFiles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface StoredFileRepo : JpaRepository<StoredFiles, UUID> {
    fun findAllByUserEquals(user: User): Optional<List<StoredFiles>>
}