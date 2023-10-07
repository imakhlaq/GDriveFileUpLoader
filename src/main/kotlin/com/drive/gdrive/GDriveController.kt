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
class GDriveController @Autowired constructor(
    val gDriveService: GDriveService,
    val googleDriveServiceKT: GoogleDriveServiceKT
) {

    @PostMapping("/api/upload")
    fun uploadToGoogle(request: HttpServletRequest): ResponseEntity<String> {

        println("inside the controller")

        val folderid = "1joKnmQnEY7SudQhX_bNywe5f363SASev";

        try {
            val isMultipart = JakartaServletFileUpload.isMultipartContent(request);

            if (!isMultipart) {
                println("inside multipart")
                // Inform user about invalid request
                return ResponseEntity.ok("Not a multipart request.");
            }
            // Create a new file upload handler
            val upload = JakartaServletFileUpload();
            // Parse the request
            val iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                val item = iter.next();
                val name = item.fieldName;
                val stream = item.inputStream;

                if (!item.isFormField) {
                    val filename = item.name;
                    println("here 43")

                    // Upload file photo.jpg on drive.
                    val fileMetadata = com.google.api.services.drive.model.File();
                    fileMetadata.setName(filename);
                    fileMetadata.setMimeType("video/mp4");

                    // Specify media type and file-path for file.
                    val mediaContent = InputStreamContent(
                        "video/mp4",
                        stream
                    );

                    mediaContent.setLength(stream.available().toLong());
                    println("here 63")
                    val file =
                        googleDriveServiceKT.getInstance()?.files()?.create(fileMetadata, mediaContent)
                            ?.executeAsInputStream();
                    println("here 67")


                }
            }
        } catch (e: GoogleJsonResponseException) {
            return ResponseEntity.ok("Unable to upload file: ${e.message}");
        } catch (e: FileUploadException) {
            return ResponseEntity.ok("File upload error, ${e.message}");
        } catch (e: IOException) {
            return ResponseEntity.ok("Internal server IO error ${e.message}");
        } catch (e: Exception) {
            return ResponseEntity.ok("Internal server IO error ${e.message}");
        }

        return ResponseEntity.ok("Success");

    }


}