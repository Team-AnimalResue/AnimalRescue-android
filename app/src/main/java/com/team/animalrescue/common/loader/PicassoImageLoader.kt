package com.team.animalrescue.common.loader

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import lv.chi.photopicker.loader.ImageLoader

class PicassoImageLoader: ImageLoader {

    override fun loadImage(context: Context, view: ImageView, uri: Uri) {
        Picasso.get()
            .load(uri)
            .fit()
            .centerCrop()
            .into(view)
    }
}
