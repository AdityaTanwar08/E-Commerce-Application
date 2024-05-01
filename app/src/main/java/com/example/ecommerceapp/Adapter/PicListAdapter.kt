package com.example.ecommerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ViewholderPicListBinding

class PicListAdapter(val items: MutableList<String>, var picMain: ImageView) :
    RecyclerView.Adapter<PicListAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    inner class ViewHolder(val binding: ViewholderPicListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicListAdapter.ViewHolder {
     context = parent.context

        val binding = ViewholderPicListBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PicListAdapter.ViewHolder, position: Int) {
       Glide.with(holder.itemView.context)
           .load(items[position])
           .into(holder.binding.picList)


        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Glide.with(holder.itemView.context)
                .load(items[position])
                .into(picMain)

            if (selectedPosition == position){
                holder.binding.picLayout.setBackgroundResource(R.drawable.gery_bg_selected)
            }else{
                holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg)
            }


        }


    }

    override fun getItemCount(): Int = items.size
}