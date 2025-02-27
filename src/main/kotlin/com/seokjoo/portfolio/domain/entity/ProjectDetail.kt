package com.seokjoo.portfolio.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ProjectDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_detail_id")
    var id: Long? = null,
) : BaseEntity()