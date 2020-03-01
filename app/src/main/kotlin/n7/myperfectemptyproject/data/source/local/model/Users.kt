package n7.myperfectemptyproject.data.source.local.model

import androidx.room.*
import n7.myperfectemptyproject.data.source.local.converter.DateConverter
import java.util.*

@Entity(tableName = "Users")
data class Users(
    @PrimaryKey @ColumnInfo(name = "id") val userId: Long,
    val firstName: String,
    val lastName: String,
    val pictureUrl: String,
   @TypeConverters(DateConverter::class) val date : Date
)
