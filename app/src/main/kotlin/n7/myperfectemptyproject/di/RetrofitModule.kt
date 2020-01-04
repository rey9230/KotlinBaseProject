package n7.myperfectemptyproject.di

import n7.myperfectemptyproject.data.source.remote.retrofit.UserApi
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
