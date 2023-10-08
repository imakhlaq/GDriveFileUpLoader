package com.drive.exception.customexceptions

import org.springframework.http.HttpStatusCode

class NotAvailableException(val httpStatusCode: HttpStatusCode, override val message: String) :
    RuntimeException(message) {
}