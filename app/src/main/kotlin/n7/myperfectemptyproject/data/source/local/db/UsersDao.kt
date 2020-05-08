package n7.myperfectemptyproject.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
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

    @Query("DELETE FROM LocalUsers")
    suspend fun deleteAll()

}
