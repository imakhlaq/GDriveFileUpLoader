package com.drive.gdrive

import com.drive.auth.model.User
import com.drive.gdrive.dao.DownFileDTO
import com.drive.gdrive.service.GDriveUploadService
import com.drive.gdrive.service.MyFilesService
import com.drive.model.StoredFiles
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody


@RestController
@RequestMapping("/api")
class GDriveController @Autowired constructor(
    val gDriveUploadService: GDriveUploadService,
    val myFilesService: MyFilesService
) {

    @PostMapping("/upload")
    fun uploadToGoogleDrive(
        @AuthenticationPrincipal userDetails: User,
        request: HttpServletRequest
    ): ResponseEntity<StoredFiles?> {

        return ResponseEntity.ok(gDriveUploadService.uploadToGoogleDrive(request, userDetails));
    }


    @GetMapping("/myfiles")
    fun getMyFiles(
        @AuthenticationPrincipal userDetails: User,
    ): ResponseEntity<List<StoredFiles>> {

        return ResponseEntity.ok(myFilesService.getMyFiles(userDetails));
    }

    @PostMapping("/download")
    fun downloadFile(
        @AuthenticationPrincipal userDetails: User,
        @RequestBody downFileDTO: DownFileDTO
    ): ResponseEntity<StreamingResponseBody> {

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generic_file_name.bin")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(myFilesService.downloadFile(downFileDTO.fileId));
    }


}