package n7.myperfectemptyproject.data.source

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import n7.myperfectemptyproject.data.source.local.db.UsersDao
import n7.myperfectemptyproject.data.source.local.model.LocalUser
import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers
import n7.myperfectemptyproject.data.source.remote.retrofit.UserApi

open class Repository @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UsersDao
) {

    suspend fun getRemoteUsers(count: Int): RemoteUsers {
        return userApi.getRandomUsers(
            count,
            listOf("name", "picture", "registered").joinToString(separator = ",")
        )
    }

    suspend fun saveLocalUser(localUser: List<LocalUser>) {
        userDao.insert(localUser)
    }

    suspend fun deleteLocalUsersAll() {
        userDao.deleteAll()
    }

    fun getAllLocalUsers(): Flow<List<LocalUser>> {
        return userDao.getAllByFlow()
    }
}
