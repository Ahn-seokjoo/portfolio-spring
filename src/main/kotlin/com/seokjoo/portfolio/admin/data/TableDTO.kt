package com.seokjoo.portfolio.admin.data

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class TableDTO(
    val name: String,
    val columns: List<String>,
    val records: List<List<String>>,
) {
    companion object {
        fun <T : Any> from(classInfo: KClass<T>, entities: List<Any>, vararg filtering: String): TableDTO {
            val name = classInfo.simpleName ?: "Unknown"
            val columns = createColumns(classInfo, *filtering)
            val records = entities.map {
                columns.map { column ->
                    classInfo.memberProperties
                        .find { column.equals(it.name) }
                        ?.getter
                        ?.call(it)
                        .toString()
                }.toList()
            }.toList()

            return TableDTO(
                name = name,
                columns = columns,
                records = records
            )
        }

        private fun <T : Any> createColumns(classInfo: KClass<T>, vararg filtering: String): MutableList<String> {
            val mainColumns = classInfo.java.declaredFields
                .filter { !filtering.contains(it.name) }
                .map { it.name }
                .toMutableList()

            val baseColumn = classInfo.java.superclass.declaredFields
                .map { it.name }
                .toMutableList()
            return (mainColumns + baseColumn).toMutableList()
        }
    }
}


