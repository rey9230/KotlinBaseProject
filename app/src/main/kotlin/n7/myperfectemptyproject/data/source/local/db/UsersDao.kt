package n7.myperfectemptyproject.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import n7.myperfectemptyproject.base.BaseDao
import n7.myperfectemptyproject.data.source.local.model.LocalUser

@Dao
interface UsersDao : BaseDao<LocalUser> {

    @Query("SELECT * FROM LocalUsers")
    fun getAllByLiveData(): LiveData<List<LocalUser>>

    @Query("SELECT * FROM LocalUsers")
    fun getAllByFlow(): Flow<List<LocalUser>>

    @Query("SELECT * FROM LocalUsers")
    suspend fun getAllByCoroutines(): List<LocalUser>

    @Query("SELECT * FROM LocalUsers WHERE id=:id")
    suspend fun getById(id: Int): LocalUser

    @Query("UPDATE LocalUsers set lastName = :lastName where id = :id AND lastName<> :lastName")
    suspend fun updateLastName(id: Int, lastName: String): Int

    @Query("DELETE FROM LocalUsers")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(localUser: LocalUser) {
        deleteAll()
        insert(localUser)
    }
}
