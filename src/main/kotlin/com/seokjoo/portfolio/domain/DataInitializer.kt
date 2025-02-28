package com.seokjoo.portfolio.domain

import com.seokjoo.portfolio.domain.constant.SkillType
import com.seokjoo.portfolio.domain.entity.Achievement
import com.seokjoo.portfolio.domain.entity.Experience
import com.seokjoo.portfolio.domain.entity.ExperienceDetail
import com.seokjoo.portfolio.domain.entity.Introduction
import com.seokjoo.portfolio.domain.entity.Link
import com.seokjoo.portfolio.domain.entity.Project
import com.seokjoo.portfolio.domain.entity.ProjectDetail
import com.seokjoo.portfolio.domain.entity.ProjectSkill
import com.seokjoo.portfolio.domain.entity.Skill
import com.seokjoo.portfolio.domain.repository.AchievementRepository
import com.seokjoo.portfolio.domain.repository.ExperienceRepository
import com.seokjoo.portfolio.domain.repository.IntroductionRepository
import com.seokjoo.portfolio.domain.repository.LinkRepository
import com.seokjoo.portfolio.domain.repository.ProjectRepository
import com.seokjoo.portfolio.domain.repository.SkillRepository
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["docker"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository,
) {

    @PostConstruct
    fun initializeDate() {
        println("스프링 실행 되었다 테스트 데이터 초기화")

        val achievement = mutableListOf<Achievement>(
            Achievement(
                title = "2022 Catkao 해커톤 대상",
                description = "대상 드림",
                host = "캣카오",
                achievedDate = LocalDate.of(2025, 2, 28),
                isActive = true,
            ),
            Achievement(
                title = "202 Catkao 해커톤 최우수 대상",
                description = "최우수 대상 드림",
                host = "케이카오",
                achievedDate = LocalDate.of(2025, 4, 28),
                isActive = true,
            ),
        )
        achievementRepository.saveAll(achievement)

        val introductions = mutableListOf<Introduction>(
            Introduction(content = "저는 바보입니다", isActive = true),
            Introduction(content = "저는 바보 일수도 있습니다", isActive = true),
            Introduction(content = "저는 바보가 아닙니다", isActive = true),
        )
        introductionRepository.saveAll(introductions)

        val links = mutableListOf<Link>(
            Link(name = "Github", content = "https://github.com/Ahn-seokjoo", isActive = true),
            Link(name = "Naver", content = "https://www.naver.com", isActive = true)
        )
        linkRepository.saveAll(links)

        val experience1 = Experience(
            title = "카톨릭 대학교",
            description = "컴퓨터 공학 전공",
            startMonth = 2,
            startYear = 2015,
            endMonth = 12,
            endYear = 2021,
            isActive = true,
        ).also {
            it.addDetails(
                mutableListOf(
                    ExperienceDetail(content = "GPA 0.2/4.5", isActive = true),
                    ExperienceDetail(content = "대학교 내내 귀엽기 (성공)", isActive = true)
                )
            )
        }
        val experience2 = Experience(
            title = "주식회사 캣카오 페이",
            description = "백엔드 개발",
            startMonth = 6,
            startYear = 2022,
            isActive = true,
        ).also {
            it.addDetails(
                mutableListOf(
                    ExperienceDetail(content = "야식 피자 먹기 0.2/4.5", isActive = true),
                    ExperienceDetail(content = "회사 내내 귀엽기 (성공)", isActive = true)
                )
            )
        }
        experienceRepository.saveAll(mutableListOf(experience1, experience2))

        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
        val redis = Skill(name = "Redis", type = SkillType.DATABASE.name, isActive = true)
        val kafka = Skill(name = "Kafka", type = SkillType.TOOL.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, kotlin, redis, kafka))

        val project1 = Project(
            name = "유기묘 발견 정보 공유 서비스",
            description = "유기묘 위치의 실시간 공유, 임시보호까지 연결해주는 서비스. 구글 맵스를 연동하여 유기묘 위치 정보를 직관적으로 파악할 수 있도록 하는 사용자 경험 개선 작업.",
            startYear = 2022,
            startMonth = 9,
            endYear = 2022,
            endMonth = 12,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(content = "구글 맵스를 이용한 유기묘 잡기", url = null, isActive = true),
                ProjectDetail(content = "애플 맵스를 이용한 유기묘 잡기", url = null, isActive = true)
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = kotlin),
                ProjectSkill(project = project1, skill = redis)
            )
        )
        val project2 = Project(
            name = "반려동물 홈 카메라 움직임 감지 분석 모듈",
            description = "카메라에서 서버로 전달되는 신호를 분석하여 움직임이 감지될 경우 클라이언트에게 알림 발송 작업.",
            startYear = 2022,
            startMonth = 12,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        project2.addDetails(
            mutableListOf(
                ProjectDetail(
                    content = "PIL(Pillow) 활용하여 이미지 분석 기능 개발", url = null,
                    isActive = true
                ),
                ProjectDetail(
                    content = "알림 발송을 비동기 처리하여 이미지 분석 - 알림 발송 기능간 의존도 감소 ", url = null, isActive = true
                ),
                ProjectDetail(
                    content = "Github Repository", url = "https://github.com/Ahn-seokjoo", isActive = true
                )
            )
        )
        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = kotlin),
                ProjectSkill(project = project2, skill = redis),
                ProjectSkill(project = project2, skill = kafka)
            )
        )
        projectRepository.saveAll(mutableListOf(project1, project2))
    }
}
