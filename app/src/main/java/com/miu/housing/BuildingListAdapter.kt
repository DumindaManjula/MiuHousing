package com.miu.housing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BuildingListAdapter(private val context:Context, private val buildingDetail: List<BuildingDetail>, private val listener: OnItemClickListener): RecyclerView.Adapter<BuildingListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingListViewHolder {
        val view = if(context is MainActivity){
            LayoutInflater.from(context).inflate(R.layout.large_card_buildings,parent, false)
        } else{
            LayoutInflater.from(parent.context).inflate(R.layout.large_card_buildings, parent, false)
        }

        return BuildingListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingListViewHolder, position: Int) {
        val building = buildingDetail[position]
        holder.bind(building)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RoomListActivity2::class.java)
            intent.putExtra("building_Id", building.id)
            intent.putExtra("buildingName", building.buildingName)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = buildingDetail.size

    interface OnItemClickListener {
        fun onItemClick(building : BuildingDetail)
    }



}