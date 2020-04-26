package n7.myperfectemptyproject.ui.main.behaviour

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.dynamicanimation.animation.withSpringForceProperties
import com.google.android.material.button.MaterialButton

internal class ChangeThemeButtonBehavior(context: Context, attributeSet: AttributeSet? = null) :
    CoordinatorLayout.Behavior<MaterialButton>(context, attributeSet) {

    private var lastVisibleOnScreenState = true

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
        animateTranslation(child, dy < 0)
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    // thx to this video https://www.youtube.com/watch?v=f3Lm8iOr4mE
    private fun animateTranslation(target: View, visibleOnScreen: Boolean) {
        val targetTranslation = if (visibleOnScreen) 0f else -target.width.toFloat() - target.paddingEnd
        if (lastVisibleOnScreenState == visibleOnScreen) return
        lastVisibleOnScreenState = visibleOnScreen
        SpringAnimation(
            target,
            SpringAnimation.TRANSLATION_X,
            targetTranslation
        ).apply {
            withSpringForceProperties {
                dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                stiffness = SpringForce.STIFFNESS_MEDIUM
            }
            animateToFinalPosition(targetTranslation)
        }
    }
}
