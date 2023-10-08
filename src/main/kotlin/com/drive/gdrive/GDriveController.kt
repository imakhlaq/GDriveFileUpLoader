package com.drive.gdrive

import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.InputStreamContent
import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload
import org.apache.tomcat.util.http.fileupload.FileUploadException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.lang.Exception


@RestController
class GDriveController @Autowired constructor(val gDriveService: GDriveService) {

    @PostMapping("/api/upload")
    fun uploadToGoogleDrive(request: HttpServletRequest): ResponseEntity<String> {

        return ResponseEntity.ok(gDriveService.uploadToGoogleDrive(request));
    }
}