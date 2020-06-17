package n7.myperfectemptyproject.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorator(private val spaceSize: Int?) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        checkNotNull(spaceSize)
        require(spaceSize > 0) { "Why are you gay?" }
        with(outRect) {
            top = spaceSize
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}
