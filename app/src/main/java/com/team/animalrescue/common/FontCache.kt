package com.team.animalrescue.common

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import java.util.*

class FontCache {
    companion object{
        var fontCache : Hashtable<Int, Typeface> = Hashtable()
        fun get(context: Context, name: Int): Typeface?{
            var tf = fontCache[name]
            if(tf == null){
                tf = ResourcesCompat.getFont(context, name)
                fontCache[name] = tf
            }
            return tf
        }
    }
}
