package n7.myperfectemptyproject.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import n7.myperfectemptyproject.base.BaseDao
import n7.myperfectemptyproject.data.source.local.model.User

@Dao
interface UsersDao : BaseDao<User> {

    @Query("SELECT * FROM Users")
    fun getAllByLiveData(): LiveData<List<User>>

    @Query("SELECT * FROM Users")
    suspend fun getAllByCoroutines(): List<User>

    @Query("SELECT * FROM Users WHERE id=:id")
    suspend fun getById(id: Int): User
}
