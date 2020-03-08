package n7.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import n7.myperfectemptyproject.base.BaseViewModel
import n7.myperfectemptyproject.base.ViewModelAssistedFactory
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromLocalStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromRemoteStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.SaveUsersToLocalStoreUseCase

class MainViewModel @AssistedInject constructor(
    application: Application,
    @Assisted private val handle: SavedStateHandle,
    private val getUsersFromRemoteStoreUseCase: GetUsersFromRemoteStoreUseCase,
    private val saveUsersToLocalStoreUseCase: SaveUsersToLocalStoreUseCase,
    private val getUsersFromLocalStoreUseCase: GetUsersFromLocalStoreUseCase
) : BaseViewModel(application, handle) {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>

    private val _errorMessage = MutableLiveData<String?>(null)

    // val errorMessage: LiveData<String?> = _errorMessage.map {
    //     ApiErrorHandle.traceErrorException(it).getErrorMessage()
    //     it.toString()
    // }
    val errorMessage: LiveData<String?> = _errorMessage
    val getUsers = liveData {
        emitSource(getUsersFromLocalStoreUseCase.execute())
    }

    fun loadUser() {
        launchWithLoading {
            getUsersFromRemoteStoreUseCase.execute(10)
                .onSuccess { remoteUsers -> saveUsersToLocalStoreUseCase.execute(remoteUsers) }
                .onFailure { _errorMessage.value = it.toString(); _errorMessage.value = null }
        }
    }
}
