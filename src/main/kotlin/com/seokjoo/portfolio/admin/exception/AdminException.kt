package com.seokjoo.portfolio.admin.exception

import org.springframework.http.HttpStatus

abstract class AdminException(
    val httpStatus: HttpStatus,
    message: String,
) : RuntimeException(message)
