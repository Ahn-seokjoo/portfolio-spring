package com.seokjoo.portfolio.admin.context.project.service

import com.seokjoo.portfolio.admin.data.TableDTO
import com.seokjoo.portfolio.admin.exception.AdminBadRequestException
import com.seokjoo.portfolio.domain.entity.Project
import com.seokjoo.portfolio.domain.entity.ProjectDetail
import com.seokjoo.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service

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
}

