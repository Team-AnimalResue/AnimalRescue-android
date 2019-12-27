package com.team.animalrescue.common.views

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.team.animalrescue.R
import com.team.animalrescue.common.FontCache

class TextViewLatoRegular(context: Context, attrs: AttributeSet) : TextView(context, attrs) {
    init {
        this.typeface = FontCache.get(context, R.font.lato_regular)
    }
}
