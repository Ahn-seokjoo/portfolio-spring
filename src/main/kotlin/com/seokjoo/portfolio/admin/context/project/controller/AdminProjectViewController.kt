package com.seokjoo.portfolio.admin.context.project.controller

import com.seokjoo.portfolio.admin.context.project.service.AdminProjectService
import com.seokjoo.portfolio.admin.data.FormElementDTO
import com.seokjoo.portfolio.admin.data.SelectFormElementDTO
import com.seokjoo.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/project")
class AdminProjectViewController(
    private val adminProjectService: AdminProjectService,
) {

    @GetMapping
    fun project(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("name", 4),
            TextFormElementDTO("description", 8),
            SelectFormElementDTO("startYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("startMonth", 2, (1..12).toList()),
            SelectFormElementDTO("endYear", 5, (2010..2030).toList()),
            SelectFormElementDTO("endMonth", 5, (1..12).toList()),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("formElements", formElements)

        val detailFormElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 4),
            TextFormElementDTO("url", 6),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("detailFormElements", detailFormElements)

        val table = adminProjectService.getProjectTable()
        model.addAttribute("table", table)

        val detailTable = adminProjectService.getProjectDetailTable(null)
        model.addAttribute("detailTable", detailTable)

        val pageAttributes = mutableMapOf<String, Any>(
            "menuName" to "Projects",
            "pageName" to table.name,
            "editable" to true,
            "deletable" to false,
            "hasDetails" to true,
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}
