package n7.myperfectemptyproject

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.FrameLayout
import androidx.core.graphics.withSave
import kotlin.math.max
import kotlin.math.min

class YouTubeView(
    context: Context,
    attributeSet: AttributeSet,
) : FrameLayout(context, attributeSet) {

    companion object {
        const val MAX_SCALE = 1F
        const val MIN_SCALE = 0.7F
    }

    private var currentScale = MAX_SCALE
    private var view: View = View(context)
    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {


        override fun onScale(detector: ScaleGestureDetector): Boolean {
            currentScale *= detector.scaleFactor
            currentScale = max(MIN_SCALE, min(currentScale, MAX_SCALE))
            invalidate()
            return true
        }
    }
    private val scaleDetector = ScaleGestureDetector(context, scaleListener)

    init {
        view.setBackgroundColor(resources.getColor(R.color.myColorPrimary))
        addView(view)
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.withSave {
            scale(currentScale, currentScale, pivotX, pivotY)
            super.dispatchDraw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        return true
    }
}