package com.seokjoo.portfolio.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Achievement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null,

    var title: String,
    var description: String,
    var achievedDate: LocalDate? = null,
    var host: String,
    var isActive: Boolean,
) : BaseEntity()
