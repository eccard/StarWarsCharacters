package com.eccard.starwarscharacters.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
        setImage(false, view, name, url)
    }


    @JvmStatic
    @BindingAdapter(value = ["showImageRec","name"])
    fun showImageRec(view: ImageView, url : String, name : String) {
        setImage(true, view, name, url)
    }

    private fun setImage(
        isRectangle: Boolean,
        view: ImageView,
        name: String,
        url: String
    ) {
        val type = if (isRectangle) {
            AvatarConstants.RECTANGLE
        } else {
            AvatarConstants.CIRCLE
        }
        val placeHolder = AvatarGenerator.avatarImage(view.context, 200, type, name)
        if (!url.isBlank()) {
            view.load(url) {
                crossfade(true)
                placeholder(placeHolder)
                if (!isRectangle) {
                    transformations(CircleCropTransformation())
                }
            }
        } else {
            view.load(placeHolder)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["isMain","foundFilms"])
    fun itemListCharacter(tv: TextView, isMain : Boolean, foundFilms : String?) {

        val sBuffer = StringBuffer()
        if (isMain){
            sBuffer.append("Is main character")
        } else {
            sBuffer.append("Isn't main character")
        }

        foundFilms?.let{
            if (!it.isBlank()){
                sBuffer.append(". From: ")
                sBuffer.append(foundFilms)
            }
        }
        tv.text = sBuffer.toString()
    }
}