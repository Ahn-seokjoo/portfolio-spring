package com.seokjoo.portfolio.domain.repository

import com.seokjoo.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProjectRepository : JpaRepository<Project, Long> {
    // 이대로 쓰면 성능 박살남
    fun findAllByIsActive(isActive: Boolean): List<Project>

    // override fun findById(id: Long): Optional<Project>
}
