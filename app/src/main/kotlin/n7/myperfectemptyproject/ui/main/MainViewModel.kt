package n7.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.data.source.local.db.UsersDao
import n7.myperfectemptyproject.ui.main.domain.usecase.GetUsersFromRemoteStoreUseCase
import n7.myperfectemptyproject.ui.main.domain.usecase.SaveUsersToLocalStoreUseCase

// todo make this ViewModel as BaseViewModel
class MainViewModel @AssistedInject constructor(
    application: Application,
    @Assisted private val handle: SavedStateHandle,
    private val getUsersFromRemoteStoreUseCase: GetUsersFromRemoteStoreUseCase,
    private val saveUsersToLocalStoreUseCase: SaveUsersToLocalStoreUseCase,
    private val usersDao: UsersDao
) : AndroidViewModel(application) {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading
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
        viewModelScope.launch {
            getUsersFromRemoteStoreUseCase.execute(1)
                .onSuccess { fff ->
                    saveUsersToLocalStoreUseCase.execute(fff).onFailure {
                        _errorMessage.value = it.toString(); _errorMessage.value = null
                    }
                }
                .onFailure {
                    _errorMessage.value = it.toString(); _errorMessage.value = null
                }
        }
    }
}

interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(handle: SavedStateHandle): T
}
