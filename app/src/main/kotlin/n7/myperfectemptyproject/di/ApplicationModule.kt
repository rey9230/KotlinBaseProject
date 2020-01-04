package n7.myperfectemptyproject.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import n7.myperfectemptyproject.data.source.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object ApplicationModule {

    @Reusable
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .enableMultiInstanceInvalidation()
            .build()
    }

    @Reusable
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}
