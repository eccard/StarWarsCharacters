package com.eccard.starwarscharacters.util.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eccard.starwarscharacters.R

class SimpleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable? = null
    private var paddinInPxStart: Int = 0
    private var paddinInPxSEnd: Int = 0


    init {
        mDivider = ContextCompat.getDrawable(context,R.drawable.line_divider)
        paddinInPxStart = context.resources.getDimensionPixelOffset(R.dimen.divider_margin_start)
        paddinInPxSEnd = context.resources.getDimensionPixelOffset(R.dimen.divider_margin_end)

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + paddinInPxStart
        val right = parent.width - parent.paddingRight -paddinInPxSEnd

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            mDivider?.let{
                val bottom = top + it.intrinsicHeight
                it.setBounds(left, top, right, bottom)
                it.draw(c)

            }

        }
    }
}