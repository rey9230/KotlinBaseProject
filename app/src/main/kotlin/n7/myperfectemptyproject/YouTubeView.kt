package n7.myperfectemptyproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
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

data class Point(val x: Float, val y: Float)

class YouTubeView(
    context: Context,
    attributeSet: AttributeSet,
) : FrameLayout(context, attributeSet) {

    companion object {
        const val MAX_SCALE = 1F
        const val MIN_SCALE = 0.5F
    }

    private var interceptKeyEvents = false
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

    private fun getLocation(view: View): Point {
        val location = IntArray(2)
        view.getLocationInWindow(location)
        return Point(location[0].toFloat(), location[1].toFloat())
    }

    private fun onEnd(child: View, xvel: Float, yvel: Float) {
        var (x, y) = getLocation(child)
        x += (child.width * currentScale) / 2
        y += (child.height * currentScale) / 2
        val finalX = when {
            xvel > 20000 -> width
            xvel < -20000 -> -child.width
            xvel > 1000 -> stickToEnd(child)
            xvel < -1000 -> stickToStart(child)
            x < width / 2 -> stickToStart(child)
            else -> stickToEnd(child)
        }
        val finalY = when {
            yvel > 20000 -> height
            yvel < -20000 -> -child.height
            yvel > 1000 -> stickToBottom(child)
            yvel < -1000 -> stickToTop(child)
            y < height / 2 -> stickToTop(child)
            else -> stickToBottom(child)
        }
        val settle = dragHelper.settleCapturedViewAt(finalX, finalY)
        if (settle) child.postOnAnimation(RecursiveSettle(child, dragHelper))
    }

    private fun stickToBottom(child: View): Int {
        return height - child.marginBottom - child.height + getHeightDifference(child)
    }

    private fun stickToTop(child: View): Int {
        return child.marginTop - getHeightDifference(child)
    }

    private fun getHeightDifference(child: View) = (child.height - child.height * currentScale).toInt() / 2

    private fun stickToEnd(child: View): Int {
        return width - child.marginEnd - child.width + getWidthDifference(child)
    }

    private fun stickToStart(child: View): Int {
        return child.marginStart - getWidthDifference(child)
    }

    private fun getWidthDifference(child: View) = (child.width - child.width * currentScale).toInt() / 2

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val viewRect = Rect()
        view.getHitRect(viewRect)
        when (ev.action) {
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                interceptKeyEvents = false
                dragHelper.cancel()
                return false
            }
        }
        return viewRect.contains(ev.x.toInt(), ev.y.toInt())
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