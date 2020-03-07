package n7.myperfectemptyproject.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import n7.myperfectemptyproject.ui.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        RetrofitModule::class,
        RepositoryModule::class,
        AssistedInjectModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Application): ApplicationComponent
    }

    // get simple viewModel instance without @AssistedInject
    // val mainViewModel: MainViewModel

    // get viewModel instance with with @AssistedInject param's
    val mainViewModelFactory: MainViewModel.Factory
}
