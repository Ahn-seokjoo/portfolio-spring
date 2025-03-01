package com.seokjoo.portfolio.admin.context.introduction.service

import com.seokjoo.portfolio.admin.context.introduction.form.AdminIntroductionForm
import com.seokjoo.portfolio.admin.data.TableDTO
import com.seokjoo.portfolio.domain.entity.Introduction
import com.seokjoo.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository,
) {
    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: AdminIntroductionForm) {
        val introduction = form.toEntity()
        introductionRepository.save(introduction)
    }

    @Transactional
    fun update(id: Long, form: AdminIntroductionForm) {
        val introduction = form.toEntity(id)
        introductionRepository.save(introduction)
    }
}
