package n7.myperfectemptyproject.utils

import android.graphics.Paint
import android.os.SystemClock
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import coil.Coil
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation

@BindingAdapter("loadImageUrl", "withCache", requireAll = false)
fun ImageView.loadImageUrl(url: String?, withCache: Boolean = false) {
    val imageLoader = Coil.imageLoader(this.context)
    val builder = ImageRequest.Builder(this.context)
        .data(url)
        .target(this)
        .allowHardware(false)
        .crossfade(true)
        .transformations(RoundedCornersTransformation(10F))
    if (withCache) {
        builder.memoryCacheKey(url)
    }
    val request = builder.build()
    imageLoader.enqueue(request)
}

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:text")
fun setTextFromInt(editText: EditText, value: Int) {
    if (getTextAsInt(editText) != value) {
        editText.setText(value.toString())
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getTextAsInt(editText: EditText): Int {
    return try {
        Integer.parseInt(editText.text.toString())
    } catch (e: Exception) {
        0
    }
}

/**
 * we take any TextView properties that may be controlled by Data Binding, and apply all of them at the beginning of our adapter
 */
@BindingAdapter("asyncText", "android:textSize", requireAll = false)
fun asyncText(textView: TextView, text: CharSequence, textSize: Int?) {
    // first, set all measurement affecting properties of the text
    // (size, locale, typeface, direction, etc)
    if (textSize != null) {
        // interpret the text size as SP
        textView.textSize = textSize.toFloat()
    }
    val params = TextViewCompat.getTextMetricsParams(textView)
    (textView as AppCompatTextView).setTextFuture(
        PrecomputedTextCompat.getTextFuture(
            text,
            params,
            null
        )
    )
}

/**
 *   we can зачеркнуть string~!
 */
@BindingAdapter("completedTask")
fun setStyle(textView: TextView, enabled: Boolean) {
    if (enabled) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

// todo write custom rule for newbie developers that warn them to use only this method!
@BindingAdapter("debounceTime", "onClick", requireAll = false)
inline fun View.setOnDebouncedClickListener(debounceTimeInMilliseconds: Long = 600L, crossinline action: () -> Unit) {
    var lastActionTime = 0L
    // This is the only place in the project where we should actually use setOnClickListener
    setOnClickListener {
        val now = SystemClock.elapsedRealtime()
        val millisecondsPassed = now - lastActionTime
        val actionNotAllowed = millisecondsPassed < debounceTimeInMilliseconds
        if (actionNotAllowed) return@setOnClickListener
        action.invoke()
        lastActionTime = now
    }
}

fun View.removeOnDebouncedClickListener() {
    setOnClickListener(null)
    isClickable = false
}