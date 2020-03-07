package n7.myperfectemptyproject.data.source.local.model

import androidx.room.*
import java.util.*

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val userId: Long = 0,
    val firstName: String,
    val lastName: String,
    val pictureUrl: String,
    val date: Date?
)
