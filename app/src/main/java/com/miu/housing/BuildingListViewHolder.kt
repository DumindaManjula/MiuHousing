package com.miu.housing

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class BuildingListViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    private val buildingName:TextView = itemView.findViewById(R.id.buildingName)
    private val freeRooms: TextView = itemView.findViewById(R.id.free_rooms)
    private val buildingImage: ImageView = itemView.findViewById(R.id.building_images)

    fun bind(buildingDetail: BuildingDetail){
        buildingName.text = buildingDetail.buildingName
        freeRooms.text = buildingDetail.numberOfRooms.toString()
        buildingImage.setImageResource(buildingDetail.image)
    }
}