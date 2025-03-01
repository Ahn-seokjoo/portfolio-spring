package com.seokjoo.portfolio.admin.data

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponse<T>(status: HttpStatus) : ResponseEntity<T>(status) {
    companion object {
        fun successCreate(): ResponseEntity<Any> {
            return ResponseEntity.ok("데이터 저장 완료")
        }
        fun successUpdate(): ResponseEntity<Any> {
            return ResponseEntity.ok("데이터 수정 완료")
        }

        fun successDelete(): ResponseEntity<Any> {
            return ResponseEntity.ok("데이터 삭제 완료")
        }
    }
}
