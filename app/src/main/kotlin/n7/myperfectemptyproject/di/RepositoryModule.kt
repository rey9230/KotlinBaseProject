package n7.myperfectemptyproject.di

import dagger.Binds
import dagger.Module
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.data.source.RepositoryImpl

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun getRepository(repositoryImpl: RepositoryImpl): Repository
}
