package com.example.myperfectemptyproject.data.source.remote

import android.content.SharedPreferences
import com.example.myperfectemptyproject.data.source.Repository
import com.example.myperfectemptyproject.data.source.remote.retrofit.UserApi
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl @Inject constructor(private val userApi: UserApi) : Repository {

    suspend fun getSomething() = withContext(Dispatchers.IO) {
        userApi.getRandomUser()
    }
}
