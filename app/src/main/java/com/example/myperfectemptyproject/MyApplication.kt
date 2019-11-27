package com.example.myperfectemptyproject

import android.app.Application
import android.os.Handler
import android.os.StrictMode
import com.example.myperfectemptyproject.di.ApplicationComponent
import com.example.myperfectemptyproject.di.DaggerApplicationComponent
import com.example.myperfectemptyproject.di.DaggerComponentProvider

class MyApplication : Application(), DaggerComponentProvider {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        enableStrictMode()
        super.onCreate()
    }

    private fun enableStrictMode() {
        if (BuildConfig.DEBUG) {
            Handler().postAtFrontOfQueue {
                StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectDiskWrites().detectNetwork().detectCustomSlowCalls().detectResourceMismatches().penaltyLog().build())
                StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build())
            }
        }
    }
}
