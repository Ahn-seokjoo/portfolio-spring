package com.seokjoo.portfolio.domain.repository

import com.seokjoo.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long>