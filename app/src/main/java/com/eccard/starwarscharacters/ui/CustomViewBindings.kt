package com.eccard.starwarscharacters.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator


object CustomViewBindings {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter(value = ["showImage","name"])
    fun showImage(view: ImageView, url : String, name : String) {
        val placeHolder = AvatarGenerator.avatarImage(view.context,200,AvatarConstants.CIRCLE,name)
        if (!url.isBlank()){
            view.load(url) {
             crossfade(true)
             placeholder(placeHolder)
             transformations(CircleCropTransformation())
            }
        } else {
            view.load(placeHolder)
        }
    }
}