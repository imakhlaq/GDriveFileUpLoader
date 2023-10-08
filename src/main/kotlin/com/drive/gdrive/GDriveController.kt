package com.drive.gdrive

import com.drive.auth.model.User
import com.drive.model.StoredFiles
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class GDriveController @Autowired constructor(val gDriveService: GDriveService) {

    @PostMapping("/api/upload")
    fun uploadToGoogleDrive(
        @AuthenticationPrincipal userDetails: User,
        request: HttpServletRequest
    ): ResponseEntity<StoredFiles?> {

        return ResponseEntity.ok(gDriveService.uploadToGoogleDrive(request, userDetails));
    }
}