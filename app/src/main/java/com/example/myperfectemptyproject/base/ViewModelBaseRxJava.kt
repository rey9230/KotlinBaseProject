package com.example.myperfectemptyproject.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class ViewModelBaseRxJava(application: Application, vararg useCases: UseCaseBaseRxJava) :
    AndroidViewModel(application) {

    protected var useCaseList: MutableList<UseCaseBaseRxJava> = mutableListOf()

    init {
        useCaseList.addAll(useCases)
    }

    override fun onCleared() {
        super.onCleared()
        useCaseList.forEach { it.dispose() }
    }
}
