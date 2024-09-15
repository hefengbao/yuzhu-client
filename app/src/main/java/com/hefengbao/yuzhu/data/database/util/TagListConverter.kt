package com.hefengbao.yuzhu.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.yuzhu.data.model.Tag
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TagListConverter {
    @TypeConverter
    fun listToString(tags: List<Tag>): String {
        return Json.encodeToString(tags)
    }

    @TypeConverter
    fun stringToList(value: String): List<Tag> {
        return Json.decodeFromString(value)
    }
}