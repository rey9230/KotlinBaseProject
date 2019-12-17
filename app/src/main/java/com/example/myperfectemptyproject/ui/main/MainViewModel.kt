package com.example.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myperfectemptyproject.ui.main.domain.usecase.UseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

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
