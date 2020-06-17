package n7.myperfectemptyproject.data.source.local.model

import androidx.room.*
import java.util.*

@Entity(tableName = "LocalUsers")
data class LocalUser(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val userId: Long = 0,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "pictureUrl")
    val pictureUrl: String,
    @ColumnInfo(name = "date")
    val date: Date?
)
