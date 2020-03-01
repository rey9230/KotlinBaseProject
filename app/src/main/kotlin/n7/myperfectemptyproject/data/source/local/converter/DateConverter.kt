package n7.myperfectemptyproject.data.source.local.converter

import androidx.room.TypeConverter
import java.util.*

// converter for Room db
class DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }
}