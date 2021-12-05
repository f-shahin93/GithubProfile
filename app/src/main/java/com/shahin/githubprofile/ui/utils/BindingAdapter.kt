package com.shahin.githubprofile.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.SimpleDateFormat

@BindingAdapter("imageUrl")
fun setImageByUrl(imageView: ImageView, url: String?) {
    if (url == null) {
        imageView.setImageDrawable(null)
        return
    }
    Glide.with(imageView).load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop().into(imageView)
}

fun dateFormatter(temp: String?): String {
    if (temp.isNullOrEmpty())
        return ""

    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val output = SimpleDateFormat("dd/MM/yyyy")
    val parse = input.parse(temp)

    return parse?.let { output.format(parse) } ?: ""
}
