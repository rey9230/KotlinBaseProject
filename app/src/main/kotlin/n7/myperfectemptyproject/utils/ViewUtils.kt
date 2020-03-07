package n7.myperfectemptyproject.utils

import android.os.SystemClock
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter

// we take any TextView properties that may be controlled by Data Binding, and apply all of them at the beginning of our adapter
@BindingAdapter("asyncText", "android:textSize", requireAll = false)
fun asyncText(textView: TextView, text: CharSequence, textSize: Int?) {
    // first, set all measurement affecting properties of the text
    // (size, locale, typeface, direction, etc)
    if (textSize != null) {
        // interpret the text size as SP
        textView.textSize = textSize.toFloat()
    }
    val params = TextViewCompat.getTextMetricsParams(textView)
    (textView as AppCompatTextView).setTextFuture(PrecomputedTextCompat.getTextFuture(text, params, null))
}

//todo write custom rule for newbie developers that warn them to use only this method!
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
            action.invoke()
        }
    }
}
