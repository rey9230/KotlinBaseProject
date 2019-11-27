package com.example.myperfectemptyproject.di

import android.app.Application
import com.example.myperfectemptyproject.ui.main.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RetrofitModule::class, RepositoryModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Application): ApplicationComponent
    }

//    @Singleton
//    чтобы класс стал синглтоном нужно пометить сам класс этой аннотацией
    val mainViewModel: MainViewModel
}
