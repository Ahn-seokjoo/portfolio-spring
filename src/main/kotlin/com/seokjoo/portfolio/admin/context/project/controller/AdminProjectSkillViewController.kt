package com.seokjoo.portfolio.admin.context.project.controller

import com.seokjoo.portfolio.admin.context.project.service.AdminProjectSkillService
import com.seokjoo.portfolio.admin.data.FormElementDTO
import com.seokjoo.portfolio.admin.data.SelectFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/project/skill")
class AdminProjectSkillViewController(
    private val adminProjectSkillService: AdminProjectSkillService,
) {

    @GetMapping
    fun projectSkill(model: Model): String {
        val projectList = adminProjectSkillService.getProjectList()
        val skillList = adminProjectSkillService.getSkillList()

        val formElements = listOf<FormElementDTO>(
            SelectFormElementDTO("project", 8, projectList),
            SelectFormElementDTO("skill", 4, skillList)
        )
        model.addAttribute("formElements", formElements)

        val table = adminProjectSkillService.getProjectSkillTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
            "menuName" to "Projects",
            "pageName" to table.name,
            "editable" to false,
            "deletable" to true,
            "hasDetails" to false,
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}
