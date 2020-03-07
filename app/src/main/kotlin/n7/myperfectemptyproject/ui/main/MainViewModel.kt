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
import n7.myperfectemptyproject.data.source.local.db.UsersDao
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromRemoteStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.SaveUsersToLocalStoreUseCase

class MainViewModel @AssistedInject constructor(
    application: Application,
    @Assisted private val handle: SavedStateHandle,
    private val getUsersFromRemoteStoreUseCase: GetUsersFromRemoteStoreUseCase,
    private val saveUsersToLocalStoreUseCase: SaveUsersToLocalStoreUseCase,
    private val usersDao: UsersDao
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
        emitSource(usersDao.getAllByLiveData())
    }

    init {
        loadUser()
    }

    fun loadUser() {
        launchWithLoading {
            getUsersFromRemoteStoreUseCase.execute(1)
                .onSuccess { fff -> saveUsersToLocalStoreUseCase.execute(fff) }
                .onFailure { _errorMessage.value = it.toString(); _errorMessage.value = null }
        }
    }
}
