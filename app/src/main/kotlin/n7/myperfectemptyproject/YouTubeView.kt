package n7.myperfectemptyproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.withSave
import androidx.core.math.MathUtils
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.updateMargins
import androidx.customview.widget.ViewDragHelper
import kotlin.math.max
import kotlin.math.min

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density.toInt())

class YouTubeView(
    context: Context,
    attributeSet: AttributeSet,
) : FrameLayout(context, attributeSet) {

    companion object {
        const val MAX_SCALE = 1F
        const val MIN_SCALE = 0.7F
    }

    private var halfWidth = 0
    private var halfHeight = 0
    private var shouldInterceptTouchEvent = false
    private var currentScale = MAX_SCALE
    private var view: View = View(context).apply {
        layoutParams = LayoutParams(200.toPx, 150.toPx).apply {
            updateMargins(10.toPx, 10.toPx, 10.toPx, 10.toPx)
        }
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.black))
        addView(this)
    }
    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            currentScale *= detector.scaleFactor
            currentScale = max(MIN_SCALE, min(currentScale, MAX_SCALE))

            view.scaleX = currentScale
            view.scaleY = currentScale
            return true
        }
    }

    private val scaleDetector = ScaleGestureDetector(context, scaleListener)
    private val dragHelper = ViewDragHelper.create(this, 1.0f, object : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean = child == view
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int = MathUtils.clamp(left, 0, width - child.width)
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int = MathUtils.clamp(top, 0, height - child.height)

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {

        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            onEnd(releasedChild)
        }
    })



    private fun onEnd(child: View) {
        val location = IntArray(2)
        child.getLocationInWindow(location)
        var (x, y) = location
        x += child.width / 2
        y += child.height / 2
        val finalX = if (x < halfWidth) 0 + child.marginStart else width - child.marginEnd - child.width
        val finalY = if (y < halfHeight) 0 + child.marginTop else height - child.marginBottom - child.height
        val settle = dragHelper.settleCapturedViewAt(finalX, finalY)
        if (settle) child.postOnAnimation(RecursiveSettle(child))
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.withSave {
            scale(currentScale, currentScale, pivotX, pivotY)
            super.dispatchDraw(canvas)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        halfWidth = w / 2
        halfHeight = h / 2
        super.onSizeChanged(w, h, oldw, oldh)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_CANCEL || ev.action == MotionEvent.ACTION_UP) {
            dragHelper.cancel()
            return false
        }
        shouldInterceptTouchEvent = dragHelper.shouldInterceptTouchEvent(ev)
        return shouldInterceptTouchEvent
    }

    private inner class RecursiveSettle(private val child: View) : Runnable {
        override fun run() {
            if (dragHelper.continueSettling(true)) {
                child.postOnAnimation(this)
            } else {
                child.removeCallbacks(this)
            }
        }
    }
}