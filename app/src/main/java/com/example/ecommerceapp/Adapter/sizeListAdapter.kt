package com.example.ecommerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ViewholderPicListBinding
import com.example.ecommerceapp.databinding.ViewholderSizeBinding

class sizeListAdapter(val items: MutableList<String>) :
    RecyclerView.Adapter<sizeListAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    inner class ViewHolder(val binding: ViewholderSizeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sizeListAdapter.ViewHolder {
     context = parent.context

        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: sizeListAdapter.ViewHolder, position: Int) {

      holder.binding.sizeTxt.text = items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)



            if (selectedPosition == position){
                holder.binding.sizeLayout.setBackgroundResource(R.drawable.green_bg3)
                holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.white))
            }else{
                holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg)
                holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))

            }


        }


    }

    override fun getItemCount(): Int = items.size
}