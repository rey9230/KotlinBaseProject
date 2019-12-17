package com.example.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myperfectemptyproject.ui.main.domain.usecase.UseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(
    application: Application,
    private val useCase: UseCase
) : AndroidViewModel(application) {

    init {
        viewModelScope.launch {
            useCase.execute().onSuccess {
            }.onFailure {
            }
        }
    }
}
