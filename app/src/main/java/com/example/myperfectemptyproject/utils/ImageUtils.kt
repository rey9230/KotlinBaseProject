package com.example.myperfectemptyproject.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String?) {
    this.load(url) {
        transformations(CircleCropTransformation())
    }
}
