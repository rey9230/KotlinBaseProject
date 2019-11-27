package com.example.myperfectemptyproject.data.retrofit

import com.example.myperfectemptyproject.data.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    suspend fun getRandomUser(): User

    @GET("api/?gender=female")
    suspend fun getUsers(@Query("results") count: Int): User
}
