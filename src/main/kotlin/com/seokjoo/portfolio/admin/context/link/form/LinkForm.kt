package com.seokjoo.portfolio.admin.context.link.form

import com.seokjoo.portfolio.domain.entity.Link
import jakarta.validation.constraints.NotBlank

data class LinkForm(
    @field:NotBlank(message = "필수값입니당")
    val name: String,
    @field:NotBlank(message = "필수값입니당")
    val content: String,
    val isActive: Boolean,
) {
    fun toEntity(): Link {
        return Link(
            name = this.name,
            content = this.content,
            isActive = this.isActive,
        )
    }

    fun toEntity(id: Long?): Link {
        val link = toEntity()
        link.id = id

        return link
    }
}
