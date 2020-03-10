package n7.myperfectemptyproject.ui.main

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.base.BaseViewModel
import n7.myperfectemptyproject.base.SingleEvent
import n7.myperfectemptyproject.base.ViewModelAssistedFactory
import n7.myperfectemptyproject.ui.main.domain.usecase.DeleteAllUsersStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromLocalStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromRemoteStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.SaveUsersToLocalStoreUseCase

class MainViewModel @AssistedInject constructor(
    application: Application,
    @Assisted handle: SavedStateHandle,
    private val getUsersFromRemoteStoreUseCase: GetUsersFromRemoteStoreUseCase,
    private val saveUsersToLocalStoreUseCase: SaveUsersToLocalStoreUseCase,
    private val getUsersFromLocalStoreUseCase: GetUsersFromLocalStoreUseCase,
    private val deleteAllUsersStoreUseCase: DeleteAllUsersStoreUseCase
) : BaseViewModel(application, handle) {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>

    val getUsers = liveData { emitSource(getUsersFromLocalStoreUseCase()) }

    fun loadUser() {
        launchWithLoading {
            getUsersFromRemoteStoreUseCase(1)
                .onSuccess { remoteUsers -> saveUsersToLocalStoreUseCase(remoteUsers) }
                .onFailure { _errorMessage.value = SingleEvent(it.toString()) }
        }
    }

    fun deleteAllUsers(): Boolean {
        viewModelScope.launch {
            deleteAllUsersStoreUseCase()
        }
        return true
    }

    fun changeTheme() {
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}
