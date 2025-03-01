package com.seokjoo.portfolio.admin.context.experience.service

import com.seokjoo.portfolio.admin.context.experience.form.ExperienceForm
import com.seokjoo.portfolio.admin.data.TableDTO
import com.seokjoo.portfolio.admin.exception.AdminBadRequestException
import com.seokjoo.portfolio.domain.entity.Experience
import com.seokjoo.portfolio.domain.entity.ExperienceDetail
import com.seokjoo.portfolio.domain.repository.ExperienceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    fun save(form: ExperienceForm) {
        val experienceDetail = form.details
            ?.map { it.toEntity() }
            ?.toMutableList()
        val experience = form.toEntity()
        experience.addDetails(details = experienceDetail)

        experienceRepository.save(experience)
    }

    @Transactional
    fun update(id: Long, form: ExperienceForm) {
        val experience = experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id} 에 대항하는 값이 없음") }

        experience.update(
            title = form.title,
            description = form.description,
            startMonth = form.startMonth,
            startYear = form.startYear,
            endYear = form.endYear,
            endMonth = form.endMonth,
            isActive = form.isActive,
        )

        val detailMap = experience.details.map { it.id to it }.toMap()
        form.details?.map {
            val entity = detailMap.get(it.id)
            entity?.update(
                content = it.content,
                isActive = it.isActive
            ) ?: experience.details.add(it.toEntity())
        }
    }
}
