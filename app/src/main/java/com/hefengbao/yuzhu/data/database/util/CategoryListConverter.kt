package com.hefengbao.yuzhu.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.yuzhu.data.model.Category
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CategoryListConverter {
    @TypeConverter
    fun listToString(categories: List<Category>): String {
        return Json.encodeToString(categories)
    }

    @TypeConverter
    fun stringToList(value: String): List<Category> {
        return Json.decodeFromString(value)
    }
}