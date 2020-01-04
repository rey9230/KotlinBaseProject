package n7.myperfectemptyproject.di

import android.app.Application
import n7.myperfectemptyproject.ui.main.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RetrofitModule::class, RepositoryModule::class, AssistedInjectModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Application): ApplicationComponent
    }

//    val mainViewModel: MainViewModel

    val mainViewModelFactory: MainViewModel.Factory
}