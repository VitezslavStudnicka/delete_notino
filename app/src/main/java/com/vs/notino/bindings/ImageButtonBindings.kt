package com.vs.notino.bindings

import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import com.vs.notino.R

@BindingAdapter(value = ["favored"])
fun ImageButton.bindFavImageSource(favored: Boolean) {
    val resource = if (favored)
        R.drawable.ic_heart_fill
    else
        R.drawable.ic_heart_empty
    setImageResource(resource)
}