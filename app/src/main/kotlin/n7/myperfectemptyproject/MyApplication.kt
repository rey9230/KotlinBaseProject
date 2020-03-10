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
        registerConnectivityMonitor()
        // listen what happening with our activities (but we using single activity *rofl*)
        registerActivityLifecycleCallbacks(AnalyticsCallbacks(""))
    }


    // if we want to build cool without any problems we can enable StrickMode that alarm us if we have something to fix (all messages will come with 'StrictMode' debug tag)
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
    // while this outer class we can create different LifecycleCallbacks for our needs
    // composition over inheritance!
    // @link (https://proandroiddev.com/say-no-to-baseactivity-and-basefragment-83b156ed8998)
 class AnalyticsCallbacks(val analytics: String) : Application.ActivityLifecycleCallbacks {
     override fun onActivityPaused(activity: Activity) {

     }

     override fun onActivityStarted(activity: Activity) {

     }

     override fun onActivityDestroyed(activity: Activity) {

     }

     override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

     }

     override fun onActivityStopped(activity: Activity) {

     }

     override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

     }

     override fun onActivityResumed(activity: Activity) {

     }
 }
