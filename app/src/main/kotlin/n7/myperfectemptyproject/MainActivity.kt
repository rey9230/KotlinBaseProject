package n7.myperfectemptyproject

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import n7.myperfectemptyproject.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private val viewmodel by viewModels<SimpleLifeCycleAwareObservable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        viewmodel.liveData.observe(this) {}
    }
}

// cool idea how to listen changes from SharedPreference with automatic subscription/unsubscription that prevent memory leaks
class SimpleLifeCycleAwareObservable(application: Application) : AndroidViewModel(application) {

    internal val liveData: LiveData<Boolean> = object : MutableLiveData<Boolean>(),
        SharedPreferences.OnSharedPreferenceChangeListener {
        val sp = PreferenceManager.getDefaultSharedPreferences(application)

        override fun onActive() {
            sp.registerOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
//            if (MainActivity.KEY == key) {
//                value = sp.getBoolean(MainActivity.KEY, false)
//            }
        }

        override fun onInactive() {
            sp.unregisterOnSharedPreferenceChangeListener(this)
        }
    }
}
