package com.seokjoo.portfolio.admin.advice

import com.seokjoo.portfolio.admin.exception.AdminException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AdminApiControllerAdvice {
    private val logger = LoggerFactory.getLogger(AdminApiControllerAdvice::class.java)

    @ExceptionHandler
    fun handleException(e: AdminException): ResponseEntity<String> {
        logger.info(e.message, e)
        return ResponseEntity.status(e.httpStatus).body(e.message)
    }

    @ExceptionHandler
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<String> {
        logger.info(e.message, e)

        val fieldError = e.bindingResult.fieldErrors.getOrNull(0)
        val message = "[${fieldError?.field} ${fieldError?.defaultMessage}]"

        return ResponseEntity.badRequest().body(message)
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String> {
        logger.info(e.message, e)

        return ResponseEntity.internalServerError().body("시스템 오류 발생")
    }
}
