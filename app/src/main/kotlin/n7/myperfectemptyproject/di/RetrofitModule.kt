package n7.myperfectemptyproject.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import n7.myperfectemptyproject.BuildConfig
import n7.myperfectemptyproject.data.source.remote.retrofit.UserApi
import n7.myperfectemptyproject.utils.logPlease
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RetrofitModule {

    private const val randomUserBaseUrl = "https://randomuser.me/"

    @Provides
    @Reusable
    fun provideRandomUser(client: OkHttpClient, moshi: Moshi): UserApi = Retrofit.Builder()
        .baseUrl(randomUserBaseUrl)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(UserApi::class.java)

    @Provides
    @Reusable
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                logPlease(message)
            }
        })
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }
}
