package n7.myperfectemptyproject

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import n7.myperfectemptyproject.di.ApplicationComponent
import n7.myperfectemptyproject.di.DaggerApplicationComponent
import n7.myperfectemptyproject.di.DaggerComponentProvider
import n7.myperfectemptyproject.utils.NetworkStateHolder.registerConnectivityMonitor

class MyApplication : Application(), DaggerComponentProvider {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        enableStrictMode()
        super.onCreate()
//        registerActivityLifecycle()
        registerConnectivityMonitor()
    }

    // крутой способ отслеживать статус всех активи
    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityDestroyed(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityStopped(activity: Activity) {}

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

            override fun onActivityResumed(activity: Activity) {}
        })
    }

    // если где-то есть проблемы, то мы о них узнаем сразу!
    private fun enableStrictMode() {
        if (BuildConfig.DEBUG) {
            Handler().postAtFrontOfQueue {
                StrictMode.setThreadPolicy(
                    StrictMode.ThreadPolicy.Builder()
                        .detectDiskWrites()
                        .detectNetwork()
                        .detectCustomSlowCalls()
                        .detectResourceMismatches()
                        .penaltyLog()
                        .build()
                )
                StrictMode.setVmPolicy(
                    StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build()
                )
            }
        }
    }
}
