package com.seokjoo.portfolio.admin.context.experience.service

import com.seokjoo.portfolio.admin.data.TableDTO
import com.seokjoo.portfolio.admin.exception.AdminBadRequestException
import com.seokjoo.portfolio.domain.entity.Experience
import com.seokjoo.portfolio.domain.entity.ExperienceDetail
import com.seokjoo.portfolio.domain.repository.ExperienceRepository
import org.springframework.stereotype.Service

@Service
class AdminExperienceService(
    private val experienceRepository: ExperienceRepository,
) {
    fun getExperienceTable(): TableDTO {
        val classInfo = Experience::class
        val entities = experienceRepository.findAll()

        return TableDTO.from(classInfo, entities, "details")
    }

    fun getExperienceDetailTable(id: Long?): TableDTO {
        val classInfo = ExperienceDetail::class
        val entities = if (id != null) {
            experienceRepository.findById(id)
                .orElseThrow { throw AdminBadRequestException("Id ${id}에 해당하는 데이터가 없어요") }
                .details
        } else {
            emptyList()
        }
        return TableDTO.from(classInfo, entities)
    }
}
