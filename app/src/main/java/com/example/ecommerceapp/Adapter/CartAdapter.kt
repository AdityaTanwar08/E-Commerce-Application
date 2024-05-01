package com.example.ecommerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.Helper.ChangeNumberItemsListener
import com.example.ecommerceapp.Helper.ManagementCart
import com.example.ecommerceapp.Model.ItemsModel
import com.example.ecommerceapp.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelcted:ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?=null
):RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    class ViewHolder(val binding: ViewholderCartBinding):RecyclerView.ViewHolder(binding.root) {

    }

    private val managementCart = ManagementCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item = listItemSelcted[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "$${item.price}"
        holder.binding.totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()


        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.picCart)

        holder.binding.plusCartBtn.setOnClickListener {
            managementCart.plusItem(listItemSelcted,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })

        }

        holder.binding.minusCartBtn.setOnClickListener {
            managementCart.minusItem(listItemSelcted,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })

        }
    }

    override fun getItemCount(): Int = listItemSelcted.size
}