package n7.myperfectemptyproject.data.source.remote

import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.data.source.remote.retrofit.UserApi
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl @Inject constructor(private val userApi: UserApi) : Repository {

    suspend fun getSomething() = withContext(Dispatchers.IO) {
        userApi.getRandomUser()
    }
}
