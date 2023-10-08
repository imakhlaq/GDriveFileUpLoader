package com.drive.gdrive

import com.drive.auth.model.User
import com.drive.gdrive.service.GDriveUploadService
import com.drive.gdrive.service.MyFilesService
import com.drive.model.StoredFiles
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class GDriveController @Autowired constructor(
    val gDriveUploadService: GDriveUploadService,
    val myFilesService: MyFilesService
) {

    @PostMapping("/api/upload")
    fun uploadToGoogleDrive(
        @AuthenticationPrincipal userDetails: User,
        request: HttpServletRequest
    ): ResponseEntity<StoredFiles?> {

        return ResponseEntity.ok(gDriveUploadService.uploadToGoogleDrive(request, userDetails));
    }


    @GetMapping("/api/myfiles")
    fun getMyFiles(
        @AuthenticationPrincipal userDetails: User,
    ): ResponseEntity<List<StoredFiles>> {

        return ResponseEntity.ok(myFilesService.getMyfiles(userDetails));
    }


}