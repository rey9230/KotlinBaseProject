package n7.myperfectemptyproject.data.source.local.converter

import androidx.room.TypeConverter
import java.util.Date

// converter for Room db
class DateConverter {

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
