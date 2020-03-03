package n7.myperfectemptyproject.utils

import android.os.SystemClock
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("setOnDebouncedClickListener")
fun View.setOnDebouncedClickListener(action: () -> Unit) {
    val actionDebounce = ActionDebounce(action)

    // This is the only place in the project where we should actually use setOnClickListener
    setOnClickListener {
        actionDebounce.notifyAction()
    }
}

fun View.removeOnDebouncedClickListener() {
    setOnClickListener(null)
    isClickable = false
}

private class ActionDebounce(private val action: () -> Unit) {

    companion object {
        const val DEBOUNCE_INTERVAL_MILLISECONDS = 600L
    }

    private var lastActionTime = 0L

    fun notifyAction() {
        val now = SystemClock.elapsedRealtime()

        val millisecondsPassed = now - lastActionTime
        val actionAllowed = millisecondsPassed > DEBOUNCE_INTERVAL_MILLISECONDS
        lastActionTime = now

        if (actionAllowed) {
            logPlease("$millisecondsPassed")
            action.invoke()
        }
    }
}
