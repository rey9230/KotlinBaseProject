@file:Suppress("PropertyName")

package n7.myperfectemptyproject.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.ui.main.MainViewModel

open class BaseViewModel(application: Application, val savedStateHandle: SavedStateHandle? = null) :
    AndroidViewModel(application) {

    protected val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun launchWithLoading(block: suspend CoroutineScope.() -> Unit) {
        try {
            _isLoading.value = true
            viewModelScope.launch {
                block()
            }
        } finally {
            _isLoading.value = false
        }
    }
}

// used to inject variable from any class we want
interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(handle: SavedStateHandle): T
}