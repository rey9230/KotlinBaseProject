@file:Suppress("PropertyName")

package n7.myperfectemptyproject.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

open class BaseViewModel(application: Application, val savedStateHandle: SavedStateHandle? = null) :
    AndroidViewModel(application) {

    private val singleThreadContext = Executors.newSingleThreadExecutor { target ->
        Thread(target, "singleThreadContext")
    }.asCoroutineDispatcher()

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    protected val _errorMessage = MutableLiveData<SingleEvent<String>>()
    val errorMessage: LiveData<SingleEvent<String>> = _errorMessage
    // val errorMessage: LiveData<String?> = _errorMessage.map {
    //     ApiErrorHandle.traceErrorException(it).getErrorMessage()
    //     it.toString()
    // }

    fun launchWithLoading(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(singleThreadContext) {
            _isLoading.postValue(true)
            block()
            _isLoading.postValue(false)
        }
    }

    override fun onCleared() {
        singleThreadContext.close()
        super.onCleared()
    }
}

// used to inject SavedStateHandle from any class we want
interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(handle: SavedStateHandle): T
}
