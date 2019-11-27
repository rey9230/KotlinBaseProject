package com.example.myperfectemptyproject.di

import com.example.myperfectemptyproject.data.retrofit.UserApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RetrofitModule {

    @Provides
    @Reusable
    fun provideRandomUser(): UserApi =
        Retrofit.Builder().baseUrl("https://*/").addConverterFactory(
            MoshiConverterFactory.create()
        ).build().create(UserApi::class.java)
}
