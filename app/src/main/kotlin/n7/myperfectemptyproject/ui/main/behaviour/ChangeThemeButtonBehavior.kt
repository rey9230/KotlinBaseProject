package n7.myperfectemptyproject.ui.main.behaviour

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.button.MaterialButton

internal class ChangeThemeButtonBehavior(context: Context, attributeSet: AttributeSet? = null) :
    CoordinatorLayout.Behavior<MaterialButton>(context, attributeSet) {

    var isHiding = false
    var oa: ObjectAnimator? = null

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: MaterialButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: MaterialButton,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (dy > 0 && !isHiding) hiding(child)
        if (dy < 0 && isHiding) showing(child)
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    private fun hiding(target: View) {
        isHiding = true
        oa = ObjectAnimator.ofFloat(
            target,
            View.TRANSLATION_X,
            -target.width.toFloat() - target.paddingEnd
        ).apply {
            duration = 300
        }
        oa?.start()
    }

    private fun showing(target: View) {
        isHiding = false
        oa = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, 0f).apply {
            duration = 300
        }
        oa?.start()
    }
}