package n7.myperfectemptyproject.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> Fragment.viewLifecycle(): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, LifecycleObserver {

        private var binding: T? = null

        init {
            this@viewLifecycle
                .lifecycle
                .addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        private fun onDestroy() {
            binding = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            return this.binding!!
        }

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
            this.binding = value
        }

    }