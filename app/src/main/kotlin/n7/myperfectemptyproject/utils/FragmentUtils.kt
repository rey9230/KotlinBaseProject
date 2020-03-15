package n7.myperfectemptyproject.utils

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import n7.myperfectemptyproject.R


fun <T> unsafeLazy(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

fun Fragment.setOnBackPressExit() {
    val millisForExit = 2000L
    var killActivity = false
    requireActivity().onBackPressedDispatcher.addCallback(this) {
        if (killActivity) {
            requireActivity().finish()
        } else {
            killActivity = true
            view?.showSnackbar(getString(R.string.all_press_again_for_exit))
            lifecycleScope.launch { delay(millisForExit); killActivity = false }
        }
    }
}