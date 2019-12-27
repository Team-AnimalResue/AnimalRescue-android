package com.team.animalrescue.rescue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.animalrescue.R
import kotlinx.android.synthetic.main.add_image_holder.view.*
import kotlinx.android.synthetic.main.image_holder.view.image_view

class AddImageViewHolder(inflater: LayoutInflater,
                         parent: ViewGroup,
                         val listener: AddPhotosAdapter.Listener) : RecyclerView.ViewHolder(inflater.inflate(R.layout.add_image_holder, parent, false)){

    fun bind() {
        itemView.add_image_layout.setOnClickListener {
            listener.onAddClicked()
        }
        }

    fun unbind() {
    }

    interface Listener {
        fun onAddClicked()
    }
}
