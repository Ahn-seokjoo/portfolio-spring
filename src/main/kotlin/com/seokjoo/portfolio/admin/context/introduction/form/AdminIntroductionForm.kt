package com.seokjoo.portfolio.admin.context.introduction.form

import com.seokjoo.portfolio.domain.entity.Introduction
import jakarta.validation.constraints.NotBlank

data class AdminIntroductionForm(
    @field:NotBlank(message = "필수값입니당")
    val content: String,
    val isActive: Boolean,
) {
    fun toEntity(): Introduction {
        return Introduction(
            content = this.content,
            isActive = this.isActive,
        )
    }

    fun toEntity(id: Long?): Introduction {
        val introduction = toEntity()
        introduction.id = id

        return introduction
    }
}
