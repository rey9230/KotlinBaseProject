package com.example.myperfectemptyproject.data.source.remote

import com.example.myperfectemptyproject.data.retrofit.UserApi
import com.example.myperfectemptyproject.data.source.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(userApi: UserApi) : Repository {

    init {
        userApi
    }
}
