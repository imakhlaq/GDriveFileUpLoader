package com.drive.gdrive.service

import com.drive.auth.model.User
import com.drive.exception.customexceptions.FileNotFoundException
import com.drive.gdrive.repo.StoredFileRepo
import com.drive.model.StoredFiles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class MyFilesService @Autowired constructor(val storedFileRepo: StoredFileRepo) {

    fun getMyfiles(userDetails: User): List<StoredFiles> {

        return storedFileRepo.findAllByUserEquals(userDetails)
            .orElseThrow { FileNotFoundException(HttpStatus.BAD_REQUEST, "NO file found") };
    }
}