package com.seokjoo.portfolio.domain.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

@Entity
class Experience(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    var id: Long? = null,

    var title: String,
    var description: String,
    var startYear: Int,
    var startMonth: Int,
    var endYear: Int? = null,
    var endMonth: Int? = null,
    var isActive: Boolean,
    /**
     * 영속성 컨텍스트 => ALL 이면 experience에 대해서 그 자식엔티티도 같이 전파되는 설정
     */
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "experience_id")
    val details: MutableList<ExperienceDetail> = mutableListOf(),
) : BaseEntity() {
    fun getEndYearMonth(): String {
        return if (endYear == null || endMonth == null) "Present"
        else "${endYear}.${endMonth}" // 2023.11
    }

    fun update(
        title: String,
        description: String,
        startYear: Int,
        startMonth: Int,
        endYear: Int?,
        endMonth: Int?,
        isActive: Boolean,
    ) {
        this.title = title
        this.description = description
        this.startYear = startYear
        this.startMonth = startMonth
        this.endMonth = endMonth
        this.endYear = endYear
        this.isActive = isActive
    }

    fun addDetails(details: MutableList<ExperienceDetail>?) {
        details?.let {
            this.details.addAll(details)
        }
    }
}
