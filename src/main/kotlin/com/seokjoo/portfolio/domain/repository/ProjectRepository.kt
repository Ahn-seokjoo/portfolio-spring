package com.seokjoo.portfolio.domain.repository

import com.seokjoo.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long>