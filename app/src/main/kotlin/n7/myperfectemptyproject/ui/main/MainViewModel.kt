package n7.myperfectemptyproject.ui.main

import android.app.Application
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.R
import n7.myperfectemptyproject.base.BaseViewModel
import n7.myperfectemptyproject.ui.main.domain.usecase.DeleteAllUsersLocalUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromLocalStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromRemoteStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.SaveUsersToLocalStoreUseCase

class MainViewModel @ViewModelInject constructor(
    application: Application,
    @Assisted handle: SavedStateHandle,
    private val getUsersFromRemoteStoreUseCase: GetUsersFromRemoteStoreUseCase,
    private val saveUsersToLocalStoreUseCase: SaveUsersToLocalStoreUseCase,
    private val getUsersFromLocalStoreUseCase: GetUsersFromLocalStoreUseCase,
    private val deleteAllUsersLocalUseCase: DeleteAllUsersLocalUseCase
) : BaseViewModel(application, handle) {

    val getUsers = getUsersFromLocalStoreUseCase().asLiveData()

    fun loadUser() {
        launchWithLoading {
            getUsersFromRemoteStoreUseCase(1)
                .onSuccess { remoteUsers -> saveUsersToLocalStoreUseCase(remoteUsers) }
                .onFailure { _errorMessage.value = it.toString() }
        }
    }

    fun deleteAllUsers(): Boolean {
        viewModelScope.launch {
            deleteAllUsersLocalUseCase()
        }
        return true
    }

    fun navigateToFeatureOne(view: View): Boolean {
        view.findNavController().navigate(R.id.action_mainFragment_to_featureOne)
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
