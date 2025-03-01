package com.seokjoo.portfolio.admin.context.achievement.form

import com.seokjoo.portfolio.domain.entity.Achievement
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class AchievementForm(
    @field:NotBlank(message = "필수값입니당")
    val title: String,
    val description: String,
    val host: String,
    val achievedDate: String,
    val isActive: Boolean,
) {
    fun toEntity(): Achievement {
        return Achievement(
            title = this.title,
            description = this.description,
            host = this.host,
            achievedDate = LocalDate.parse(this.achievedDate),
            isActive = this.isActive,
        )
    }

    fun toEntity(id: Long?): Achievement {
        val achievement = toEntity()
        achievement.id = id

        return achievement
    }
}
