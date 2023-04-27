package com.miu.housing

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.db.Room

class RoomDetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val roomNumber:TextView = itemView.findViewById(R.id.room_number)
    private val roomType: TextView = itemView.findViewById(R.id.single_shared)
    private val roomPrice:TextView = itemView.findViewById(R.id.room_price)
    private val roomImage:ImageView = itemView.findViewById(R.id.room_images)

    fun bind(room: Room){
        roomNumber.text = room.Room_no.toString()
        roomType.text = room.Room_type
        roomPrice.text = room.price.toString()
        roomImage.setImageResource(room.image)
    }
}