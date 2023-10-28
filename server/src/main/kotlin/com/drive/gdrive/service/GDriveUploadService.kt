package com.drive.gdrive.service

import com.drive.auth.model.User
import com.drive.exception.customexceptions.FileNotFoundException
import com.drive.gdrive.GDriveUtil
import com.drive.gdrive.repo.StoredFileRepo
import com.drive.model.StoredFiles
import com.google.api.client.http.InputStreamContent
import jakarta.servlet.http.HttpServletRequest
import jakarta.transaction.Transactional
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
class GDriveUploadService @Autowired constructor(val gDriveUtil: GDriveUtil, val storedFileRepo: StoredFileRepo) {


    @Transactional
    fun uploadToGoogleDrive(request: HttpServletRequest, userDetails: User): StoredFiles? {

        //TODO implemet store in db

        val folderId = "1joKnmQnEY7SudQhX_bNywe5f363SASev";

        val isMultipart = JakartaServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            // Inform user about invalid request
            throw FileNotFoundException(HttpStatus.BAD_REQUEST, "File is not Present")
        }

        // Create a new file upload handler
        val upload = JakartaServletFileUpload();

        println("in upload")

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
                fileMetadata.setMimeType(item.contentType);

                // Specify media type and file-path for file.
                val mediaContent = InputStreamContent(
                    item.contentType,
                    stream
                );

                val fileRes =
                    gDriveUtil.getInstance()?.files()?.create(fileMetadata, mediaContent)?.execute()
                stream.close()

                //TODO implemet store in db

                val file = StoredFiles(
                    fileRes?.name,
                    fileRes?.id,
                    item.contentType,
                    fileRes?.createdTime,
                    userDetails
                )

                val fileInDB = storedFileRepo.save(file);
                return fileInDB;

            } else {
                stream.close();
                throw FileNotFoundException(HttpStatus.BAD_REQUEST, "File is not Present")
            }
        }
        return null;
    }


}