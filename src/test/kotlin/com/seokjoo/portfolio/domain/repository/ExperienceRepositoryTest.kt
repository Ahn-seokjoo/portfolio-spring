package com.seokjoo.portfolio.domain.repository

import com.seokjoo.portfolio.domain.entity.Experience
import com.seokjoo.portfolio.domain.entity.ExperienceDetail
import org.assertj.core.api.Assertions
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.math.exp
import kotlin.test.Test

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExperienceRepositoryTest(
    @Autowired val experienceRepository: ExperienceRepository,
) {

    private val DATA_SIZE = 10

    private fun createExperience(n: Int): Experience {
        val experience = Experience(
            title = "${n}",
            description = "test 설명 ${n}",
            startYear = 2023,
            startMonth = 2,
            endYear = 2024,
            endMonth = 9,
            isActive = true,
        )

        val details = mutableListOf<ExperienceDetail>()
        for (i in 1..n) {
            val experienceDetail = ExperienceDetail(content = "테스트 ${i}", isActive = true)
            details.add(experienceDetail)
        }
        experience.addDetails(details)
        return experience
    }

    @BeforeAll
    fun beforeAll() {
        println(" 데이터 초기화 이전 조회 시작")
        val beforeInitialize = experienceRepository.findAll()
        Assertions.assertThat(beforeInitialize).hasSize(0)
        println(" 데이터 초기화 이전 조회 끝")

        println(" 데이터 초기화 시작 ")
        val experiences = mutableListOf<Experience>()
        for (i in 1..DATA_SIZE) {
            val experience = createExperience(i)
            experiences.add(experience)
        }
        experienceRepository.saveAll(experiences)
        println(" 데이터 초기화 끝 ")
    }

    @Test
    fun testFindAll() {
        println(" finAll 테스트 시작 전")
        val experiences = experienceRepository.findAll()
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            println("experience.detals.size = ${experience.details.size}")
        }
        println(" finAll 테스트 끝 ${experiences.size}")
    }

    @Test
    fun testFindAllByIsActive() {
        println(" finAllByIsActive 테스트 시작 전")
        val experiences = experienceRepository.findAllByIsActive(true)
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            println("experience.detals.size = ${experience.details.size}")
        }
        println(" finAll 테스트 끝 ${experiences.size}")
    }

}
