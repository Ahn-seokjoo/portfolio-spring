package com.seokjoo.portfolio.admin.context.introduction.controller

import com.seokjoo.portfolio.admin.context.introduction.service.AdminIntroductionService
import com.seokjoo.portfolio.admin.data.FormElementDTO
import com.seokjoo.portfolio.admin.data.SelectFormElementDTO
import com.seokjoo.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/introduction")
class AdminIntroductionViewController(
    private val adminIntroductionService: AdminIntroductionService,
) {
    @GetMapping
    fun introduction(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 10),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("formElements", formElements)

        val table = adminIntroductionService.getIntroductionTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
            "menuName" to "Index",
            "pageName" to table.name,
            "editable" to true,
            "deletable" to false,
            "hasDetails" to false,
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}
