package com.example.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myperfectemptyproject.data.source.remote.ApiErrorHandle
import com.example.myperfectemptyproject.ui.main.domain.usecase.UseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(
    application: Application,
    private val useCase: UseCase
) : AndroidViewModel(application) {

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        viewModelScope.launch {
            useCase.execute()
                .onSuccess {

                }
                .onFailure {
                    _errorMessage.value = ApiErrorHandle.traceErrorException(it).getErrorMessage()
                }
        }
    }
}
