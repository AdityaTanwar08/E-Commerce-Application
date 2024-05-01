package com.example.ecommerceapp.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerceapp.Adapter.PicListAdapter
import com.example.ecommerceapp.Adapter.sizeListAdapter
import com.example.ecommerceapp.Helper.ManagementCart
import com.example.ecommerceapp.Model.ItemsModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityDetailBinding


class DetailActivity : baseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)

        getBundle()

        initList()
    }

    private fun initList() {

        val sizeList = ArrayList<String>()
        for (size in item.size) {
            sizeList.add(size.toString())
        }

        binding.sizelist.adapter = sizeListAdapter(sizeList)
        binding.sizelist.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl.toString())
        }

        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

        binding.picList.adapter = PicListAdapter(colorList, binding.picMain)
        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.sellerNameTxt.text = item.sellerName

        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managementCart.insertItems(item)


            binding.backBtn.setOnClickListener { finish() }
            binding.cartBtn.setOnClickListener {
                startActivity(Intent(this@DetailActivity, CartActivity::class.java))
            }

            Glide.with(this)
                .load(item.sellerpic)
                .apply(RequestOptions().transform(CenterCrop()))
                .into(binding.picSeller)

            binding.msgToSellerBtn.setOnClickListener {
                val sendData = Intent(Intent.ACTION_VIEW)
                sendData.setData(Uri.parse("sms:" + item.sellertell))
                sendData.putExtra("sms_body", "type your message")
                startActivity(intent)
            }

            binding.callToSellerBtn.setOnClickListener {
                val phone = item.sellertell.toString()

                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
                startActivity(intent)


            }


        }


    }
}