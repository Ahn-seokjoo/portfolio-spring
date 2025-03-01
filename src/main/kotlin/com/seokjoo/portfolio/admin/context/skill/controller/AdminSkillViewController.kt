package com.seokjoo.portfolio.admin.context.skill.controller

import com.seokjoo.portfolio.admin.context.skill.service.AdminSkillService
import com.seokjoo.portfolio.admin.data.FormElementDTO
import com.seokjoo.portfolio.admin.data.SelectFormElementDTO
import com.seokjoo.portfolio.admin.data.TextFormElementDTO
import com.seokjoo.portfolio.domain.constant.SkillType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/skill")
class AdminSkillViewController(
    private val skillService: AdminSkillService,
) {
    @GetMapping
    fun skill(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("title", 2),
            SelectFormElementDTO("type", 2, SkillType.values().map { it.name }),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("formElements", formElements)

        val table = skillService.getSkillTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
            "menuName" to "Resume",
            "pageName" to table.name,
            "editable" to true,
            "deletable" to false,
            "hasDetails" to false,
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}
