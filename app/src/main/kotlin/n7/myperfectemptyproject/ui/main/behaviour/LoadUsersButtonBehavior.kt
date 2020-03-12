package n7.myperfectemptyproject.ui.main.behaviour

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat
import com.google.android.material.button.MaterialButton

// ух блять я ёбаный далбаёб 20 минут думал де ошибка а она тута, забыл вторичный конструктор переопределить :(((((((((((((
// ууууууууууу блять так ещё нужно в xml полный путь указывать, или эта шлюха пишет что attributeSet `Could not inflate Behavior subclass`
internal class LoadUsersButtonBehavior(context: Context, attributeSet: AttributeSet? = null) :
    CoordinatorLayout.Behavior<MaterialButton>(context, attributeSet) {

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
        val offset = MathUtils.clamp(child.translationX + dy, 0f, child.width.toFloat() + child.paddingEnd)
        if (offset != child.translationY) child.translationX = offset
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}