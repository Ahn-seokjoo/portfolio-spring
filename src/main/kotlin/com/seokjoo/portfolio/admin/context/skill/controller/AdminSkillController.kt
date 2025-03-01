package com.seokjoo.portfolio.admin.context.skill.controller

import com.seokjoo.portfolio.admin.context.skill.form.SkillForm
import com.seokjoo.portfolio.admin.context.skill.service.AdminSkillService
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
@RequestMapping("/admin/api/skills")
class AdminSkillController(
    private val skillService: AdminSkillService,
) {

    @PostMapping
    fun postAchievement(@RequestBody @Validated form: SkillForm): ResponseEntity<Any> {
        skillService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putAchievement(@PathVariable id: Long, @RequestBody @Validated form: SkillForm): ResponseEntity<Any> {
        skillService.update(id = id, form = form)

        return ApiResponse.successUpdate()
    }
}
