package com.seokjoo.portfolio.presentation.repository

import com.seokjoo.portfolio.domain.entity.Achievement
import com.seokjoo.portfolio.domain.entity.Experience
import com.seokjoo.portfolio.domain.entity.Introduction
import com.seokjoo.portfolio.domain.entity.Link
import com.seokjoo.portfolio.domain.entity.Project
import com.seokjoo.portfolio.domain.entity.Skill
import com.seokjoo.portfolio.domain.repository.AchievementRepository
import com.seokjoo.portfolio.domain.repository.ExperienceRepository
import com.seokjoo.portfolio.domain.repository.IntroductionRepository
import com.seokjoo.portfolio.domain.repository.LinkRepository
import com.seokjoo.portfolio.domain.repository.ProjectRepository
import com.seokjoo.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Repository

@Repository
class PresentationRepository(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository,
) {
    fun getAchievements(): List<Achievement> {
        return achievementRepository.findAllByIsActive(isActive = true)
    }

    fun getIntroductions(): List<Introduction> {
        return introductionRepository.findAllByIsActive(isActive = true)
    }

    fun getLinks(): List<Link> {
        return linkRepository.findAllByIsActive(isActive = true)
    }

    fun getSkills(): List<Skill> {
        return skillRepository.findAllByIsActive(isActive = true)
    }

    fun getProjects(): List<Project> {
        return projectRepository.findAllByIsActive(isActive = true)
    }

    fun getExperiences(): List<Experience> {
        return experienceRepository.findAllByIsActive(isActive = true)
    }
}
