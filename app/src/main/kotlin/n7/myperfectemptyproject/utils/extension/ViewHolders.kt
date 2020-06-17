package n7.myperfectemptyproject.utils.extension

import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.dynamicanimation.animation.withSpringForceProperties
import androidx.recyclerview.widget.RecyclerView

// xD https://youtu.be/BNcODK-Ju0g
fun RecyclerView.ViewHolder.animateTranslationX() {
    SpringAnimation(itemView, SpringAnimation.TRANSLATION_X, 0f).apply {
        withSpringForceProperties {
            dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }
        setStartValue(300f).start()
    }
}

fun RecyclerView.ViewHolder.animateRotation() {
    SpringAnimation(itemView, DynamicAnimation.ROTATION, 0f).apply {
        withSpringForceProperties {
            dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }
        setStartVelocity(-25f)
        setStartValue(-5f)
        start()
    }
}
