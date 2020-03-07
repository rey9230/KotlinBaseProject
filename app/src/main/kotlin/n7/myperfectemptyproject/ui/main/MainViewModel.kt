package n7.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.asCoroutineDispatcher
import n7.myperfectemptyproject.base.BaseViewModel
import n7.myperfectemptyproject.base.ViewModelAssistedFactory
import n7.myperfectemptyproject.data.source.local.db.UsersDao
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromRemoteStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.SaveUsersToLocalStoreUseCase
import java.util.concurrent.Executors

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

    fun loadUser() {
        launchWithLoading {
            getUsersFromRemoteStoreUseCase.execute(10)
                .onSuccess { remoteUsers -> saveUsersToLocalStoreUseCase.execute(remoteUsers) }
                .onFailure { _errorMessage.value = it.toString(); _errorMessage.value = null }
        }
    }
}
