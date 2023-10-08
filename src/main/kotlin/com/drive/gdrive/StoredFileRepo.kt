package com.drive.gdrive

import com.drive.model.StoredFiles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StoredFileRepo : JpaRepository<StoredFiles, UUID> {
}