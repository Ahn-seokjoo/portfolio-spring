package com.seokjoo.portfolio.presentation.service

import com.seokjoo.portfolio.presentation.dto.IntroductionDTO
import com.seokjoo.portfolio.presentation.dto.LinkDTO
import com.seokjoo.portfolio.presentation.dto.ProjectDTO
import com.seokjoo.portfolio.presentation.dto.ResumeDTO
import com.seokjoo.portfolio.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository,
) {
    @Transactional(readOnly = true)
    fun getIntroductions(): List<IntroductionDTO> {
        val introductions = presentationRepository.getIntroductions()
        return introductions.map { IntroductionDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getLinks(): List<LinkDTO> {
        val links = presentationRepository.getLinks()
        return links.map { LinkDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getResume(): ResumeDTO {
        val experiences = presentationRepository.getExperiences()
        val achievements = presentationRepository.getAchievements()
        val skills = presentationRepository.getSkills()
        return ResumeDTO(
            experiences = experiences,
            achievements = achievements,
            skills = skills,
        )
    }

    @Transactional(readOnly = true)
    fun getProjects(): List<ProjectDTO> {
        val projects = presentationRepository.getProjects()

        return projects.map { ProjectDTO(it) }
    }
}
