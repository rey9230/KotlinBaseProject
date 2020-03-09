package n7.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import n7.myperfectemptyproject.base.BaseViewModel
import n7.myperfectemptyproject.base.SingleEvent
import n7.myperfectemptyproject.base.ViewModelAssistedFactory
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromLocalStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromRemoteStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.SaveUsersToLocalStoreUseCase

class MainViewModel @AssistedInject constructor(
    application: Application,
    @Assisted handle: SavedStateHandle,
    private val getUsersFromRemoteStoreUseCase: GetUsersFromRemoteStoreUseCase,
    private val saveUsersToLocalStoreUseCase: SaveUsersToLocalStoreUseCase,
    private val getUsersFromLocalStoreUseCase: GetUsersFromLocalStoreUseCase
) : BaseViewModel(application, handle) {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>

    val getUsers = liveData { emitSource(getUsersFromLocalStoreUseCase()) }

    fun loadUser() {
        launchWithLoading {
            getUsersFromRemoteStoreUseCase(10)
                .onSuccess { remoteUsers -> saveUsersToLocalStoreUseCase(remoteUsers) }
                .onFailure { _errorMessage.value = SingleEvent(it.toString()) }
        }
    }
}

// does this class better than simple _isLoading???
@Suppress("DataClassPrivateConstructor")
data class LoadingState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.RUNNING)
        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}
