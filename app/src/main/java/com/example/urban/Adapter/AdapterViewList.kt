package com.example.urban.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urban.R
import com.example.urban.model.ViewListItem
import java.io.File

class AdapterViewList (var context: Context, var arrayList: MutableList<ViewListItem>) : RecyclerView.Adapter<AdapterViewList.ItemHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.view_list_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val item = arrayList.get(position)
        holder.icon.setImageResource(item.iconName)
        holder.viewListItemName.text = item.itemname
    }

    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var icon = itemView.findViewById<ImageView>(R.id.view_list_icon_image)
        var viewListItemName = itemView.findViewById<TextView>(R.id.view_list_item_name)
    }


}