package com.eccard.starwarscharacters.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class IntListTypeConverter {

    companion object {

        var gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToSomeObjectList(data: String?): List<Int?> {
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type =
                object : TypeToken<List<Int?>?>() {}.getType()
            return gson.fromJson<List<Int?>>(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<Int?>?): String? {
            return gson.toJson(someObjects)
        }
    }
}