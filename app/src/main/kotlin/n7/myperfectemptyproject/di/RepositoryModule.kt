package n7.myperfectemptyproject.di

import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.data.source.remote.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun getRepository(repositoryImpl: RepositoryImpl): Repository
}
