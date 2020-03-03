package n7.myperfectemptyproject.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.RoundedCornersTransformation

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String?) {
    this.load(url) {
        crossfade(true)
        transformations(RoundedCornersTransformation(10F))
    }
}
