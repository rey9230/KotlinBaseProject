package n7.myperfectemptyproject.data.source

import kotlinx.coroutines.delay
import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers
import n7.myperfectemptyproject.data.source.remote.retrofit.UserApi
import javax.inject.Inject

open class RepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : Repository {

    override suspend fun getRemoteUsers(count : Int): RemoteUsers {
        delay(5000)
        return userApi.getRandomUsers(
            count,
            listOf("name", "picture", "registered").joinToString(separator = ",")
        )
    }

}
