package ru.appkode.tbi.data.storage.db.converters

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

internal class DatabaseTypeConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toLocalDateTime(value: Long?): LocalDateTime? {
            return value?.let { LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC) }
        }

        @TypeConverter
        @JvmStatic
        fun fromLocalDateTime(dateTime: LocalDateTime): Long {
            return dateTime.toEpochSecond(ZoneOffset.UTC)
        }
    }
}
