package com.drive.exception.customexceptions

import org.springframework.http.HttpStatusCode

class FileNotFoundException(val httpStatusCode: HttpStatusCode, override val message: String) :
    RuntimeException(message) {
}