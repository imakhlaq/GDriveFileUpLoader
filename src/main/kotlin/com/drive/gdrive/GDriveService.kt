package com.drive.gdrive

import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.InputStreamContent
import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload
import org.apache.tomcat.util.http.fileupload.FileUploadException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.lang.Exception


@Service
class GDriveService @Autowired constructor(val gDriveUtil: GDriveUtil) {

    fun uploadToGoogleDrive(request: HttpServletRequest): String {

        val folderId = "1joKnmQnEY7SudQhX_bNywe5f363SASev";

        try {
            val isMultipart = JakartaServletFileUpload.isMultipartContent(request);

            if (!isMultipart) {
                // Inform user about invalid request
                return "Not a multipart request."
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

                    // Upload file photo.jpg on drive.
                    val fileMetadata = com.google.api.services.drive.model.File();
                    fileMetadata.setName(filename);
                    fileMetadata.setMimeType("video/mp4");

                    // Specify media type and file-path for file.
                    val mediaContent = InputStreamContent(
                        "video/mp4",
                        stream
                    );

                    val file =
                        gDriveUtil.getInstance()?.files()?.create(fileMetadata, mediaContent)?.executeAsInputStream()
                }
            }
        } catch (e: GoogleJsonResponseException) {
            return "Unable to upload file: ${e.message}"
        } catch (e: FileUploadException) {
            return "File upload error, ${e.message}"
        } catch (e: IOException) {
            return "Internal server IO error ${e.message}"
        } catch (e: Exception) {
            return "Internal server IO error ${e.message}"
        }

        return "Success"

    }
}