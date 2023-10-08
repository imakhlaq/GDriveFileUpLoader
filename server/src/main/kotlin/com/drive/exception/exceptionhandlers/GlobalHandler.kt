package com.drive.exception.exceptionhandlers

import com.drive.exception.ErrorPOJO
import com.drive.exception.customexceptions.FileNotFoundException
import com.drive.exception.customexceptions.NotAvailableException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalHandler {

    @ExceptionHandler(FileNotFoundException::class)
    fun handleCustom(e: FileNotFoundException): ResponseEntity<ErrorPOJO> {

        val error = ErrorPOJO(e.httpStatusCode, e.message, LocalDateTime.now());
        return ResponseEntity.ok(error);
    }


    @ExceptionHandler(NotAvailableException::class)
    fun handleCustom(e: NotAvailableException): ResponseEntity<ErrorPOJO> {

        val error =
            ErrorPOJO(HttpStatus.INTERNAL_SERVER_ERROR, e.message, LocalDateTime.now());
        return ResponseEntity.ok(error);

    }

    @ExceptionHandler(Exception::class)
    fun handleCustom(e: Exception): ResponseEntity<ErrorPOJO> {

        val error =
            ErrorPOJO(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "Something went wrong", LocalDateTime.now());
        return ResponseEntity.ok(error);

    }
}