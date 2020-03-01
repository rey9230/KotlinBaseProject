package n7.myperfectemptyproject.data.source.remote.retrofit

import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    suspend fun getRandomUsers(
        @Query("results") results: Int,
        @Query("inc") inc: String
    ): RemoteUsers
}
