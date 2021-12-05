package com.shahin.githubprofile.ui.utils

import android.content.Context
import android.graphics.Rect
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shahin.githubprofile.R

fun marginItemDecoration(
    marginLeft: Int = 0,
    marginRight: Int = 0,
    marginTop: Int = 0,
    marginBottom: Int = 0,
) = object : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            left = marginLeft
            right = marginRight
            top = marginTop
            bottom = marginBottom
        }
    }

}

fun Context.setSpannableText(
    text: String,
    textView: AppCompatTextView,
    onClick: (() -> Unit)? = null,
    isUnderlineText: Boolean,
    startIndex: Int,
    endIndex: Int,
) {
    val spannableText = SpannableString(text)
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClick?.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = ContextCompat.getColor(this@setSpannableText, R.color.purple_500)
            ds.isUnderlineText = isUnderlineText
        }
    }

    spannableText.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.text = spannableText
    textView.movementMethod = LinkMovementMethod.getInstance()

}