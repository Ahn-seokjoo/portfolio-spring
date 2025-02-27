package com.seokjoo.portfolio.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

    @CreatedDate // JPA 엔티티가 생성된 시간을 자동으로 기록
    @Column(nullable = false, updatable = false)
    var createdDateTime: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedDateTime: LocalDateTime = LocalDateTime.now()
}