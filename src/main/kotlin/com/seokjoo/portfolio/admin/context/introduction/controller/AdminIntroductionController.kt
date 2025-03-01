package com.seokjoo.portfolio.admin.context.introduction.controller

import com.seokjoo.portfolio.admin.context.introduction.form.AdminIntroductionForm
import com.seokjoo.portfolio.admin.context.introduction.service.AdminIntroductionService
import com.seokjoo.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/introductions")
class AdminIntroductionController(
    private val introductionsService: AdminIntroductionService,
) {

    @PostMapping
    fun postAchievement(@RequestBody @Validated form: AdminIntroductionForm): ResponseEntity<Any> {
        introductionsService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putAchievement(
        @PathVariable id: Long,
        @RequestBody @Validated form: AdminIntroductionForm,
    ): ResponseEntity<Any> {
        introductionsService.update(id = id, form = form)

        return ApiResponse.successUpdate()
    }
}
