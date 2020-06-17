package n7.myperfectemptyproject.data.source.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "LocalUserPets",
    foreignKeys = [ForeignKey(entity = LocalUser::class, parentColumns = arrayOf("id"), childColumns = arrayOf("owner"))]
)
data class LocalUserPet(
    @PrimaryKey
    val petId: Long,
    val name: String,
    val owner: Long
)
