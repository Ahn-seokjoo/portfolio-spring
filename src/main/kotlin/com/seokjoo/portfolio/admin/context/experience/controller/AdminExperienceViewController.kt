package com.seokjoo.portfolio.admin.context.experience.controller

import com.seokjoo.portfolio.admin.context.experience.service.AdminExperienceService
import com.seokjoo.portfolio.admin.data.FormElementDTO
import com.seokjoo.portfolio.admin.data.SelectFormElementDTO
import com.seokjoo.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/experience")
class AdminExperienceViewController(
    private val adminExperience: AdminExperienceService,
) {

    @GetMapping
    fun experience(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("title", 4),
            TextFormElementDTO("description", 8),
            SelectFormElementDTO("startYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("startMonth", 2, (1..12).toList()),
            SelectFormElementDTO("endYear", 5, (2010..2030).toList()),
            SelectFormElementDTO("endMonth", 5, (1..12).toList()),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("formElements", formElements)

        val detailFormElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 10),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("detailFormElements", detailFormElements)

        val table = adminExperience.getExperienceTable()
        model.addAttribute("table", table)

        val detailTable = adminExperience.getExperienceDetailTable(null)
        model.addAttribute("detailTable", detailTable)

        val pageAttributes = mutableMapOf<String, Any>(
            "menuName" to "Resume",
            "pageName" to table.name,
            "editable" to true,
            "deletable" to false,
            "hasDetails" to true,
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}
