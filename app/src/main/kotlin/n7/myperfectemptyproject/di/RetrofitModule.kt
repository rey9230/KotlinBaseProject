package n7.myperfectemptyproject.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import n7.myperfectemptyproject.data.source.remote.retrofit.UserApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RetrofitModule {

    private const val randomUserBaseUrl = "https://randomuser.me/api/"

    @Provides
    @Reusable
    fun provideRandomUser(): UserApi =
        Retrofit.Builder().baseUrl(randomUserBaseUrl).addConverterFactory(
            MoshiConverterFactory.create()
        ).build().create(UserApi::class.java)
}
