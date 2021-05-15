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
        const val MIN_SCALE = 0.5F
    }

    private var halfWidth = 0
    private var halfHeight = 0
    private var shouldInterceptTouchEvent = false
    private var currentScale = MAX_SCALE
    private var view: View = View(context).apply {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 150.toPx).apply {
            updateMargins(10.toPx, 10.toPx, 10.toPx, 10.toPx)
        }
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.black))
        addView(this)
    }
    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            currentScale *= detector.scaleFactor
            currentScale = max(MIN_SCALE, min(currentScale, MAX_SCALE))

            view.pivotY = view.height / 2f
            view.pivotX = view.width / 2f
            // view.animate().scaleY(currentScale).scaleX(currentScale).setDuration(0).start()
            view.scaleX = currentScale
            view.scaleY = currentScale
            return true
        }
    }

    private val scaleDetector = ScaleGestureDetector(context, scaleListener)
    private val dragHelper = ViewDragHelper.create(this, 1f, object : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean = child == view
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int = left
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int = top
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) = onEnd(releasedChild, xvel, yvel)
    })

    private fun onEnd(child: View, xvel: Float, yvel: Float) {
        val location = IntArray(2)
        child.getLocationInWindow(location)
        var (x, y) = location
        x += child.width / 2
        y += child.height / 2
        val finalX = when {
            xvel > 10000 -> width
            xvel < -10000 -> -child.width
            xvel > 1000 -> width - child.marginEnd - child.width
            xvel < -1000 -> 0 + child.marginStart
            x < halfWidth -> 0 + child.marginStart
            else -> width - child.marginEnd - child.width
        }
        val finalY = when {
            yvel > 20000 -> height
            yvel < -20000 -> -child.height
            yvel > 1000 -> height - child.marginBottom - child.height
            yvel < -1000 -> 0 + child.marginTop
            y < halfWidth -> 0 + child.marginTop
            else -> height - child.marginBottom - child.height
        }
        val settle = dragHelper.settleCapturedViewAt(finalX, finalY)
        if (settle) child.postOnAnimation(RecursiveSettle(child, dragHelper))
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

    private class RecursiveSettle(
        private val child: View,
        private val dragHelper: ViewDragHelper,
    ) : Runnable {
        override fun run() {
            if (dragHelper.continueSettling(true)) {
                child.postOnAnimation(this)
            } else {
                child.removeCallbacks(this)
            }
        }
    }
}