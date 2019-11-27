package com.example.myperfectemptyproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myperfectemptyproject.data.source.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    val repository: Repository
) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
}
