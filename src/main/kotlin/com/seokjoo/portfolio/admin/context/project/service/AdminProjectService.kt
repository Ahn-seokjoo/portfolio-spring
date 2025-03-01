package com.seokjoo.portfolio.admin.context.project.service

import com.seokjoo.portfolio.admin.context.project.form.ProjectForm
import com.seokjoo.portfolio.admin.data.TableDTO
import com.seokjoo.portfolio.admin.exception.AdminBadRequestException
import com.seokjoo.portfolio.domain.entity.Project
import com.seokjoo.portfolio.domain.entity.ProjectDetail
import com.seokjoo.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminProjectService(
    private val projectRepository: ProjectRepository,
) {
    fun getProjectTable(): TableDTO {
        val classInfo = Project::class
        val entities = projectRepository.findAll()

        return TableDTO.from(classInfo, entities, "details")
    }

    fun getProjectDetailTable(id: Long?): TableDTO {
        val classInfo = ProjectDetail::class
        val entities = if (id != null) {
            projectRepository.findById(id)
                .orElseThrow { throw AdminBadRequestException("Id ${id}에 해당하는 데이터가 없어요") }
                .details
        } else {
            emptyList()
        }
        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: ProjectForm) {
        val experienceDetail = form.details
            ?.map { it.toEntity() }
            ?.toMutableList()
        val experience = form.toEntity()
        experience.addDetails(details = experienceDetail)

        projectRepository.save(experience)
    }

    @Transactional
    fun update(id: Long, form: ProjectForm) {
        val experience = projectRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id} 에 대항하는 값이 없음") }

        experience.update(
            name = form.name,
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
                url = it.url,
                isActive = it.isActive
            ) ?: experience.details.add(it.toEntity())
        }
    }
}

