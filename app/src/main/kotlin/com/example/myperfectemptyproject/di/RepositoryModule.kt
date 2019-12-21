package com.example.myperfectemptyproject.di

import com.example.myperfectemptyproject.data.source.Repository
import com.example.myperfectemptyproject.data.source.remote.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun getRepository(repositoryImpl: RepositoryImpl): Repository
}
