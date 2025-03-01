package com.seokjoo.portfolio.admin.context.link.controller

import com.seokjoo.portfolio.admin.context.link.service.AdminLinkService
import com.seokjoo.portfolio.admin.data.FormElementDTO
import com.seokjoo.portfolio.admin.data.SelectFormElementDTO
import com.seokjoo.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/link")
class AdminLinkViewController(
    private val adminLinkService: AdminLinkService,
) {
    @GetMapping
    fun link(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("name", 2),
            TextFormElementDTO("content", 8),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())),
        )
        model.addAttribute("formElements", formElements)

        val table = adminLinkService.getLinkTable()
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
