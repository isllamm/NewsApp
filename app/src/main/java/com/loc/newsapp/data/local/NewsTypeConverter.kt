package com.loc.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverters
import com.loc.newsapp.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverters
    fun sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }
    @TypeConverters
    fun stringToSource(source: String): Source{
        return source.split(",").let{strings ->
            Source(strings[0], strings[1])
        }
    }
}