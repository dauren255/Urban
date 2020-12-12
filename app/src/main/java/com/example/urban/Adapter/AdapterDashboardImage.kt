package com.example.urban.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.urban.R
import java.io.File

class AdapterDashboardImage(var context: Context, var arrayList: ArrayList<String>) : RecyclerView.Adapter<AdapterDashboardImage.ItemHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.dashboard_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        Glide.with(context).load(R.drawable.logistics).into(holder.icon)

    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var icon = itemView.findViewById<ImageView>(R.id.dashboardImageItem)
    }
}