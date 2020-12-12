package com.example.urban.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.urban.R
import java.io.File

class AdapterPhoto(var context: Context, var arrayList: ArrayList<File>) : RecyclerView.Adapter<AdapterPhoto.ItemHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout_list_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        var file: File = arrayList.get(position)
        val takenImage = BitmapFactory.decodeFile(file.absolutePath)
        holder.icon.setImageBitmap(takenImage)
    }

    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var icon = itemView.findViewById<ImageView>(R.id.icon_image)
    }
}