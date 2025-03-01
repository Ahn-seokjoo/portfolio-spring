package com.seokjoo.portfolio.admin.context.achievement.controller

import com.seokjoo.portfolio.admin.context.achievement.form.AchievementForm
import com.seokjoo.portfolio.admin.context.achievement.service.AdminAchievementService
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
@RequestMapping("/admin/api/achievements")
class AdminAchievementController(
    private val adminAchievementService: AdminAchievementService,
) {

    @PostMapping
    fun postAchievement(@RequestBody @Validated form: AchievementForm): ResponseEntity<Any> {
        adminAchievementService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putAchievement(@PathVariable id: Long, @RequestBody @Validated form: AchievementForm): ResponseEntity<Any> {
        adminAchievementService.update(id = id, form = form)

        return ApiResponse.successUpdate()
    }
}
