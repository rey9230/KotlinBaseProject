package n7.myperfectemptyproject.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocalModel")
data class LocalModel(
    @PrimaryKey val id: Long
)
