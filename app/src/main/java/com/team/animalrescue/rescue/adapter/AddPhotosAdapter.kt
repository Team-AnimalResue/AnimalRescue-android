package com.team.animalrescue.rescue.adapter

import  android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class AddPhotosAdapter(private var context: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listener = Listener.NO_OP
    private var selectedUriList: ArrayList<Uri>? = null

    init {
        selectedUriList = ArrayList()
        selectedUriList?.add(0, Uri.EMPTY)
    }
    fun setItems(selectedUriList: ArrayList<Uri>?){
        this.selectedUriList?.addAll(selectedUriList as ArrayList)
        notifyDataSetChanged()
    }
    fun removeItem(position: Int){
        this.selectedUriList?.removeAt(position)
        notifyItemRemoved(position)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> AddImageViewHolder(inflater, parent, listener)
            else -> ImageViewHolder(inflater, parent, listener)
        }
    }

    override fun getItemCount(): Int {
        return selectedUriList?.size ?: 1
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> {
                (holder as AddImageViewHolder).bind()
            }
            else -> {
                (holder as ImageViewHolder).bind(selectedUriList?.get(position), context)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface Listener : AddImageViewHolder.Listener, ImageViewHolder.Listener {
        companion object {
            val NO_OP = object : Listener {
                override fun onAddClicked() {
                    //NO OP
                }

                override fun onRemoveClicked(position: Int) {
                    //NO OP
                }


            }
        }
    }


}
