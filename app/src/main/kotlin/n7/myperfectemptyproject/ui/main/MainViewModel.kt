package n7.myperfectemptyproject.ui.main

import android.app.Application
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.R
import n7.myperfectemptyproject.base.BaseViewModel
import n7.myperfectemptyproject.ui.main.domain.usecase.DeleteAllUsersLocalUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromLocalStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromRemoteStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.SaveUsersToLocalStoreUseCase

class MainViewModel @AssistedInject constructor(
    application: Application,
    @Assisted handle: SavedStateHandle,
    @Assisted private val id: Int,
    private val getUsersFromRemoteStoreUseCase: GetUsersFromRemoteStoreUseCase,
    private val saveUsersToLocalStoreUseCase: SaveUsersToLocalStoreUseCase,
    private val getUsersFromLocalStoreUseCase: GetUsersFromLocalStoreUseCase,
    private val deleteAllUsersLocalUseCase: DeleteAllUsersLocalUseCase,
) : BaseViewModel(application, handle) {

    @AssistedFactory
    interface Factory {
        fun create(state: SavedStateHandle, id: Int): MainViewModel
    }

    class LambdaFactory<T : ViewModel>(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        private val create: (handle: SavedStateHandle) -> T,
    ) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
        override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
            return create.invoke(handle) as T
        }
    }

    val getUsers = getUsersFromLocalStoreUseCase().asLiveData()

    fun loadUser() = viewModelScope.launch {
        val usersFromRemoteStoreUseCase = getUsersFromRemoteStoreUseCase(1)
        saveUsersToLocalStoreUseCase(usersFromRemoteStoreUseCase)
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
