package com.seokjoo.portfolio.admin.context.introduction.service

import com.seokjoo.portfolio.admin.data.TableDTO
import com.seokjoo.portfolio.domain.entity.Introduction
import com.seokjoo.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service

@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository,
) {
    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}
