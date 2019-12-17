package com.example.myperfectemptyproject.data.source.remote

import com.example.myperfectemptyproject.data.source.remote.retrofit.UserApi
import com.example.myperfectemptyproject.data.source.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val userApi: UserApi) : Repository {

    suspend fun getSomething() = withContext(Dispatchers.IO) {
        userApi.getRandomUser()
    }

}
