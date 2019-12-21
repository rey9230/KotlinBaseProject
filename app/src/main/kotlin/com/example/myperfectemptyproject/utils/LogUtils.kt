package com.example.myperfectemptyproject.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

fun FragmentActivity.addLogObserver() {
    this.lifecycle.addObserver(LogUtils())
}

fun Fragment.addLogObserver() {
    this.lifecycle.addObserver(LogUtils())
}

fun log(message: String) {
    Log.d("StrictMode", message)
}

class LogUtils : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {
        log("$source - $event")
    }
}
