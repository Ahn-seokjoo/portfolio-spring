package com.seokjoo.portfolio.admin.context.skill.form

import com.seokjoo.portfolio.domain.entity.Skill
import jakarta.validation.constraints.NotBlank

data class SkillForm(
    @field:NotBlank(message = "필수값입니당")
    val name: String,
    @field:NotBlank(message = "필수값입니당")
    val type: String,
    val isActive: Boolean,
) {
    fun toEntity(): Skill {
        return Skill(
            name = this.name,
            type = this.type,
            isActive = this.isActive,
        )
    }

    fun toEntity(id: Long?): Skill {
        val skill = toEntity()
        skill.id = id

        return skill
    }
}
