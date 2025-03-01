package com.seokjoo.portfolio.admin.exception

import org.springframework.http.HttpStatus

abstract class AdminException(
    val httpStatus: HttpStatus,
    message: String,
) : RuntimeException(message)

class AdminBadRequestException(message: String) : AdminException(httpStatus = HttpStatus.BAD_REQUEST, message = message)

class AdminInternalServerErrorException(message: String) :
    AdminException(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, message = message)
