package com.drive.gdrive.service

import com.drive.auth.model.User
import com.drive.exception.customexceptions.FileNotFoundException
import com.drive.gdrive.GDriveUtil
import com.drive.gdrive.repo.StoredFileRepo
import com.drive.model.StoredFiles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.InputStream


@Service
class MyFilesService @Autowired constructor(val storedFileRepo: StoredFileRepo, val gDriveUtil: GDriveUtil) {

    fun getMyfiles(userDetails: User): List<StoredFiles> {

        return storedFileRepo.findAllByUserEquals(userDetails)
            .orElseThrow { FileNotFoundException(HttpStatus.BAD_REQUEST, "NO file found") };
    }

    fun downloadFile(fileId: String): StreamingResponseBody {

        val file = gDriveUtil.getInstance()?.Files()?.get(fileId)?.executeAsInputStream();

        return StreamingResponseBody { outputStream ->
            val buffer = ByteArray(1024)
            var bytesRead: Int = 0
            while (bytesRead != -1) {
                bytesRead = file?.read(buffer) as Int
                outputStream.write(buffer, 0, bytesRead)
            }
            file?.close()
            outputStream.flush()
        }

    }
}


