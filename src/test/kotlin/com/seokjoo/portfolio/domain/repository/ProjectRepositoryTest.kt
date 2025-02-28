package com.seokjoo.portfolio.domain.repository

import com.seokjoo.portfolio.domain.constant.SkillType
import com.seokjoo.portfolio.domain.entity.Project
import com.seokjoo.portfolio.domain.entity.ProjectDetail
import com.seokjoo.portfolio.domain.entity.ProjectSkill
import com.seokjoo.portfolio.domain.entity.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectRepositoryTest(
    @Autowired val projectRepository: ProjectRepository,
    @Autowired val skillRepository: SkillRepository,
) {
    private val DATA_SIZE = 10

    private fun createProject(n: Int): Project {
        val project = Project(
            name = "${n}",
            description = "테스트 설명 ${n}",
            startMonth = 9,
            endYear = 2024,
            startYear = 2023,
            endMonth = 2,
            isActive = true,
        )

        val details = mutableListOf<ProjectDetail>()
        for (i in 1..n) {
            val projectDetail = ProjectDetail(content = "테스트 ${n}", url = null, isActive = true)
            details.add(projectDetail)
        }
        project.addDetails(details)

        val skills = skillRepository.findAll()
        val skillsUsedInProject = skills.subList(0, n)

        for (skill in skillsUsedInProject) {
            val projectSkill = ProjectSkill(project = project, skill = skill)
            project.skills.add(projectSkill)
        }

        return project
    }

    @BeforeAll
    fun beforeAll() {
        println(" ---- skill data 초기화 시작 ---")

        val skills = mutableListOf<Skill>()
        for (i in 1..DATA_SIZE) {
            val skillTypes = SkillType.values()
            val skill = Skill(name = "테스트 ${i}", type = skillTypes[i % skillTypes.size].name, isActive = true)
            skills.add(skill)
        }
        skillRepository.saveAll(skills)
        println(" ---- skill data 초기화 끝 ---")

        println(" --- 데이터 초기화 시작 ---")
        val experiences = mutableListOf<Project>()
        for (i in 1..DATA_SIZE) {
            val experience = createProject(i)
            experiences.add(experience)
        }
        projectRepository.saveAll(experiences)
        println(" ---- 데이터 초기화 끝 ----")
    }

    @Test
    fun testFindAll() {
        println(" finAll 테스트 시작 전")
        val experiences = projectRepository.findAll()
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.name.toInt())
            println("experience.detals.size = ${experience.details.size}")
        }
        println(" finAll 테스트 끝 ${experiences.size}")
    }

    @Test
    fun testFindAllByIsActive() {
        println(" finAllByIsActive 테스트 시작 전")
        val experiences = projectRepository.findAllByIsActive(true)
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.name.toInt())
            println("experience.details.size = ${experience.details.size}")

            assertThat(experience.skills).hasSize(experience.name.toInt())
            println("experience.skills.size = ${experience.details.size}")
        }
        println(" finAll 테스트 끝 ${experiences.size}")
    }
}
