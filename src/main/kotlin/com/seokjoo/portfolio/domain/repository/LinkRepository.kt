package com.seokjoo.portfolio.domain.repository

import com.seokjoo.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long>