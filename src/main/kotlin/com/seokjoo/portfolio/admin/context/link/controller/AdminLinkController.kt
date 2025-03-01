package com.seokjoo.portfolio.admin.context.link.controller

import com.seokjoo.portfolio.admin.context.link.form.LinkForm
import com.seokjoo.portfolio.admin.context.link.service.AdminLinkService
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
@RequestMapping("/admin/api/link")
class AdminLinkController(
    private val linkService: AdminLinkService,
) {

    @PostMapping
    fun postAchievement(@RequestBody @Validated form: LinkForm): ResponseEntity<Any> {
        linkService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putAchievement(@PathVariable id: Long, @RequestBody @Validated form: LinkForm): ResponseEntity<Any> {
        linkService.update(id = id, form = form)

        return ApiResponse.successUpdate()
    }
}
