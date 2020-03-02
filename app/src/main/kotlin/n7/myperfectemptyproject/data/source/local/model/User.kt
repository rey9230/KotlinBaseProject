package n7.myperfectemptyproject.data.source.local.model

import androidx.room.*
import java.util.*
import n7.myperfectemptyproject.data.source.local.converter.DateConverter

@Entity(tableName = "Users")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") val userId: Long = 0,
    val firstName: String,
    val lastName: String,
    val pictureUrl: String
    // @TypeConverters(DateConverter::class) val date: Date
)
