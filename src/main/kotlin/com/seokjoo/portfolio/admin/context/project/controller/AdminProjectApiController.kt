package com.seokjoo.portfolio.admin.context.project.controller

import com.seokjoo.portfolio.admin.context.project.form.ProjectForm
import com.seokjoo.portfolio.admin.context.project.service.AdminProjectService
import com.seokjoo.portfolio.admin.data.ApiResponse
import com.seokjoo.portfolio.admin.data.TableDTO
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/project")
class AdminProjectApiController(
    private val adminProjectService: AdminProjectService,
) {

    @PostMapping
    fun postAchievement(@RequestBody @Validated form: ProjectForm): ResponseEntity<Any> {
        adminProjectService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putAchievement(@PathVariable id: Long, @RequestBody @Validated form: ProjectForm): ResponseEntity<Any> {
        adminProjectService.update(id = id, form = form)

        return ApiResponse.successUpdate()
    }

    @GetMapping("/{id}/details")
    fun getExperienceDetails(@PathVariable id: Long): TableDTO {
        return adminProjectService.getProjectDetailTable(id)
    }
}
