package n7.myperfectemptyproject.data.source.remote.retrofit

import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import retrofit2.http.GET

interface UserApi {

    @GET("api/")
    suspend fun getRandomUser(): RemoteModel
}
