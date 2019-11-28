package com.example.myperfectemptyproject.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object ApplicationModule {

//    @Reusable
//    @Provides
//    fun provideDatabase(application: Application): PizzaDatabase {
//        return Room.databaseBuilder(
//            application, PizzaDatabase::class.java, "pizza1.db"
//        ).build()
//    }

    @Reusable
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}
