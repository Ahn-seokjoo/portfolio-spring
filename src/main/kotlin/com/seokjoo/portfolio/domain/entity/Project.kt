package com.seokjoo.portfolio.domain.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

@Entity
class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    var id: Long? = null,

    var name: String,
    var description: String,
    var startYear: Int,
    var startMonth: Int,
    var endYear: Int?,
    var endMonth: Int?,
    var isActive: Boolean,
    /**
     * 영속성 컨텍스트 => ALL 이면 experience에 대해서 그 자식엔티티도 같이 전파되는 설정
     */
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "project_id")
    val details: MutableList<ProjectDetail> = mutableListOf(),

    @OneToMany(mappedBy = "project", cascade = [CascadeType.PERSIST])
    var skills: MutableList<ProjectSkill> = mutableListOf(),
) : BaseEntity() {

    fun getEndYearMonth(): String {
        return if (endYear == null || endMonth == null) "Present"
        else "${endYear}.${endMonth}" // 2023.11
    }

    fun update(
        name: String,
        description: String,
        startYear: Int,
        startMonth: Int,
        endYear: Int?,
        endMonth: Int?,
        isActive: Boolean,
    ) {
        this.name = name
        this.description = description
        this.startYear = startYear
        this.startMonth = startMonth
        this.endMonth = endMonth
        this.endYear = endYear
        this.isActive = isActive
    }

    fun addDetails(details: MutableList<ProjectDetail>?) {
        details?.let {
            this.details.addAll(details)
        }
    }
}
