package com.example.karthickmadasamy.myapplication.utils

import android.content.Context
import android.util.AttributeSet

/**
 * This class which will resize the image depends on our Screen size
 * Created by Karthick.Madasamy on 12/4/2019.
 */

class SquareImageView : android.support.v7.widget.AppCompatImageView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        setMeasuredDimension(width, width)
    }

}
