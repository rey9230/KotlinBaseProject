package n7.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.base.ApiErrorHandle
import n7.myperfectemptyproject.ui.main.domain.usecase.UseCase

// todo make this ViewModel to BaseViewModel
class MainViewModel @AssistedInject constructor(
    application: Application,
    @Assisted private val handle: SavedStateHandle,
    private val useCase: UseCase
    // todo you should ALWAYS inject Dispatchers
//    private val defaultDispatcher: CoroutineDispatcher
) : AndroidViewModel(application) {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private val _errorMessage = MutableLiveData<Throwable?>(null)
    val errorMessage: LiveData<String?> = _errorMessage.map {
        ApiErrorHandle.traceErrorException(it).getErrorMessage()
    }

    init {
        viewModelScope.launch {
            useCase.execute()
                .onSuccess {
                }
                .onFailure {
                    _errorMessage.value = it; _errorMessage.value = null
                }
        }
    }
}

interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(handle: SavedStateHandle): T
}
