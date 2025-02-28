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

    var url: String?,
    var content: String,
    var isActive: Boolean,
) : BaseEntity() {
    // 이게 필요한가 ... public이고 set이 되는데 굳이 ...
    // 이름이 update면 자동 업데이트가 되나 싶어서 작성은 해봄
    fun update(url: String?, content: String, isActive: Boolean) {
        this.url = url
        this.content = content
        this.isActive = isActive
    }
}
