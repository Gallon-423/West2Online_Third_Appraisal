package com.example.west2online_third_appraisal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UpItemAdapter(private val upItemList:List<UpItem>):
        RecyclerView.Adapter<UpItemAdapter.ViewHolder>(){
        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val upItemName:TextView=view.findViewById(R.id.upItemName)
            val upItemImage:ImageView=view.findViewById(R.id.upItemImage)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upItem=upItemList[position];
        holder.upItemImage.setImageResource(upItem.imageId)
        holder.upItemName.text=upItem.name
    }

    override fun getItemCount()=upItemList.size

}
