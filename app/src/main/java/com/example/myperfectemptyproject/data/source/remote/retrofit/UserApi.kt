package com.example.myperfectemptyproject.data.source.remote.retrofit

import com.example.myperfectemptyproject.data.source.remote.model.RemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    suspend fun getRandomUser(): RemoteModel

}
