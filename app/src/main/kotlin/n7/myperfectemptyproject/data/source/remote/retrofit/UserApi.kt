package n7.myperfectemptyproject.data.source.remote.retrofit

import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    suspend fun getRandomUser(
        @Query("results") results: Int,
        @Query("inc") inc: String
    ): RemoteModel
}
