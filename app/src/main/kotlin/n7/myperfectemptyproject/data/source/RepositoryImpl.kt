package n7.myperfectemptyproject.data.source

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import n7.myperfectemptyproject.data.source.remote.retrofit.UserApi

open class RepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : Repository {

    override suspend fun getSomething(): RemoteModel {
        delay(5000)
       return userApi.getRandomUser()
    }

    fun getNothing(number : Int) :Int? {
        return null
    }
}
