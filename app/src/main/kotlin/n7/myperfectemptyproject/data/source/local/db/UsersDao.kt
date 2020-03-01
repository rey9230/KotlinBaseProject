package n7.myperfectemptyproject.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import n7.myperfectemptyproject.base.BaseDao
import n7.myperfectemptyproject.data.source.local.model.Users

@Dao
interface UsersDao : BaseDao<Users> {

    @Query("SELECT * FROM Users")
    fun getAllByLiveData(): LiveData<List<Users>>

    @Query("SELECT * FROM Users")
    suspend fun getAllByCoroutines(): List<Users>

    @Query("SELECT * FROM Users WHERE id=:id")
    suspend fun getById(id: Int): Users
}
