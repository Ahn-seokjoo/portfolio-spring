package com.seokjoo.portfolio.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ExperienceDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_detail_id")
    var id: Long? = null,

    var content: String,
    var isActive: Boolean,
) : BaseEntity() {
    fun update(content: String, isActive: Boolean) {
        this.content = content
        this.isActive = isActive
    }
}
