package n7.myperfectemptyproject.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

fun FragmentActivity.addLifecycleLogObserver() {
    this.lifecycle.addObserver(LogUtils())
}

fun Fragment.addLifecycleLogObserver() {
    this.lifecycle.addObserver(LogUtils())
}

fun logPlease(message: String) {
    Log.d("StrictMode", message)
}

private class LogUtils : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {
        logPlease("$source - $event")
    }
}
