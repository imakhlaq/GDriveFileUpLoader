package com.drive.gdrive

import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.apache.tomcat.util.http.fileupload.FileUploadException
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.FileOutputStream
import java.io.IOException
import javax.servlet.http.HttpServletRequest


@RestController
class GDriveController @Autowired constructor(val googleDriveService: GoogleDriveService) {


    @PostMapping("/api/upload")
    fun uploadToGoogle(request: HttpServletRequest): ResponseEntity<String> {

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
                val name = item.getFieldName();
                val stream = item.openStream();
                if (!item.isFormField()) {
                    val filename = item.getName();
                    // Process the input stream
                    val out = FileOutputStream(filename);
                    IOUtils.copy(stream, out);
                    stream.close();
                    out.close();
                }
            }
        } catch (e: FileUploadException) {
            return ResponseEntity.ok("File upload error, ${e.toString()}");
        } catch (e: IOException) {
            return ResponseEntity.ok("Internal server IO error ${e.toString()}");
        }

        return ResponseEntity.ok("Success");

    }

}