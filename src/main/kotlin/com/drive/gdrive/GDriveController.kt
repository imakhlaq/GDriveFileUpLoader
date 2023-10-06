package com.drive.gdrive

import com.google.api.client.googleapis.media.MediaHttpUploader
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener
import com.google.api.client.http.InputStreamContent
import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.apache.tomcat.util.http.fileupload.FileUploadException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedInputStream
import java.io.IOException
import java.util.Collections
import javax.servlet.http.HttpServletRequest


@RestController
class GDriveController @Autowired constructor(val googleDriveService: GoogleDriveService) {


    @PostMapping("/api/upload")
    fun uploadToGoogle(request: HttpServletRequest): ResponseEntity<String> {

        val folderid = "1joKnmQnEY7SudQhX_bNywe5f363SASev";

        try {

            val isMultipart = ServletFileUpload.isMultipartContent(request);

            if (!isMultipart) {
                // Inform user about invalid request
                return ResponseEntity.ok("Not a multipart request.");
            }

            // Create a new file upload handler
            val upload = ServletFileUpload();

            // Parse the request
            val iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                val item = iter.next();
                val name = item.fieldName;
                val stream = item.openStream();
                if (!item.isFormField) {
                    val filename = item.name;

                    // Upload file photo.jpg on drive.
                    val fileMetadata = com.google.api.services.drive.model.File();
                    fileMetadata.setName(filename).setParents(Collections.singletonList(folderid));

                    // Specify media type and file-path for file.
                    val mediaContent = InputStreamContent(
                        "video/mp4",
                        BufferedInputStream(stream)
                    );
                    mediaContent.setLength(stream.available().toLong());

                    val requestGD = googleDriveService.getInstance().files().create(fileMetadata, mediaContent)
                        .setFields("1")
                        .execute();

                    stream.close();
                }
            }
        } catch (e: FileUploadException) {
            return ResponseEntity.ok("File upload error, ${e.message}");
        } catch (e: IOException) {
            return ResponseEntity.ok("Internal server IO error ${e.message}");
        }

        return ResponseEntity.ok("Success");

    }

}