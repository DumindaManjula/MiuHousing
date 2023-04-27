package com.miu.housing

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.db.Building
import com.miu.housing.db.Room
import com.miu.housing.db.User

class RoomDetailAdapter(private val room: List<Room>, private val buildingName:String, private val user: User): RecyclerView.Adapter<RoomDetailViewHolder>() {
    private var filteredRooms: List<Room> = room


    fun filterRooms(building: Building):List<Room> {
        filteredRooms = if(building == null){
            room
        }else{
            room.filter { it.buildingName == building.buildingName}
        }

        if(filteredRooms.isEmpty()){
            filteredRooms = room
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
        val room = filteredRooms[position]
        holder.bind(room)
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, RoomActivity::class.java)
            intent.putExtra("userInfo", user)
            intent.putExtra("room",room)
            intent.putExtra("BuildingName", buildingName)
            holder.itemView.context.startActivity(intent)
        }
    }

}