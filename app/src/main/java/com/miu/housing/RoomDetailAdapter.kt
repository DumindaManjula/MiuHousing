package com.miu.housing

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RoomDetailAdapter(private val roomDetail: List<RoomDetail>, private val buildingName:String): RecyclerView.Adapter<RoomDetailViewHolder>() {
    private var filteredRooms: List<RoomDetail> = roomDetail


    fun filterRooms(building: BuildingDetail):List<RoomDetail> {
        filteredRooms = if(building == null){
            roomDetail
        }else{
            roomDetail.filter { it.BuildingId == building.id}
        }

        if(filteredRooms.isEmpty()){
            filteredRooms = roomDetail
        }
        notifyDataSetChanged()
        return  filteredRooms
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_details, parent, false)
        return RoomDetailViewHolder(view)
    }

    override fun getItemCount(): Int = filteredRooms.size


    override fun onBindViewHolder(holder: RoomDetailViewHolder, position: Int) {
        val roomDetail = filteredRooms[position]
        holder.bind(roomDetail)
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, RoomActivity::class.java)
            intent.putExtra("room",roomDetail)
            intent.putExtra("BuildingName", buildingName)
            holder.itemView.context.startActivity(intent)
        }
    }

}