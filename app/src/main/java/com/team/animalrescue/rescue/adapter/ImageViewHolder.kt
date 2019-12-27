package com.team.animalrescue.rescue.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.team.animalrescue.R
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.image_holder.view.*


class ImageViewHolder(
     inflater: LayoutInflater,
     parent: ViewGroup,
    val listener: AddPhotosAdapter.Listener) : RecyclerView.ViewHolder(inflater.inflate(R.layout.image_holder, parent, false)) {

    fun bind(uri: Uri?, context: Context?) {
        if(uri!=null){

            val radius = 40
            val margin = 0
            val transformation: Transformation = RoundedCornersTransformation(radius, margin)

            Picasso.get()
                .load(uri)
                .fit()
                .centerCrop()
                .transform(transformation)
                .into(itemView.image_view)
        }
        itemView.remove.visibility = View.VISIBLE
        itemView.remove.setOnClickListener { listener.onRemoveClicked(adapterPosition) }
    }

    fun unbind(){}

    interface Listener {
        fun onRemoveClicked(position: Int)
    }

}
