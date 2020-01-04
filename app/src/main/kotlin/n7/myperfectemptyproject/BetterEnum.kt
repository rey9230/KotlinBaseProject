package n7.myperfectemptyproject

import androidx.annotation.IntDef

class BetterEnum {

    companion object {

        @IntDef(SLOW,NORMAL,FAST)
        // The @Retention(RetentionPolicy.SOURCE) annotation tells the compiler not to store the enumerated annotation data in the .class file.
        @Retention(AnnotationRetention.SOURCE)
        annotation class Speed

        const val SLOW = 0
        const val NORMAL = 1
        const val FAST = 2
    }

    fun asdf() {
        setSpeed(SLOW)
    }

    fun setSpeed(@Speed speed: Int) {

    }

    @Speed
    fun getSpeed() :Int = SLOW

}