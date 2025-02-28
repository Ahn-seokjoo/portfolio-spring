package com.seokjoo.portfolio.presentation.dto

import com.seokjoo.portfolio.domain.entity.Introduction

data class IntroductionDTO(
    val content: String,
) {
    constructor(introduction: Introduction) : this(content = introduction.content)
}
