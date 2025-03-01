package com.seokjoo.portfolio.admin.context.achievement.service

import com.seokjoo.portfolio.admin.data.TableDTO
import com.seokjoo.portfolio.domain.entity.Achievement
import com.seokjoo.portfolio.domain.repository.AchievementRepository
import org.springframework.stereotype.Service

@Service
class AdminAchievementViewService(
    private val achievementRepository: AchievementRepository,
) {

    fun getAchievementTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = achievementRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}
