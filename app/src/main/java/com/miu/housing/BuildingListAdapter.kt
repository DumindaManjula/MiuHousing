package com.miu.housing

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.db.Building
import com.miu.housing.db.User

class BuildingListAdapter(private val context: Context, private val building: List<Building>, private val user: User): RecyclerView.Adapter<BuildingListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingListViewHolder {
        val view = if(context is MainActivity){
            LayoutInflater.from(context).inflate(R.layout.large_card_buildings,parent, false)
        } else{
            LayoutInflater.from(parent.context).inflate(R.layout.large_card_buildings, parent, false)
        }

        return BuildingListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingListViewHolder, position: Int) {
        val building = building[position]
        holder.bind(building)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RoomListActivity2::class.java)
            intent.putExtra("userInfo", user)
            intent.putExtra("building_Id", building.id)
            intent.putExtra("buildingName", building.buildingName)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = building.size

    interface OnItemClickListener {
        fun onItemClick(building : Building)
    }



}