package com.miu.housing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.ActivityRoomReservationMainBinding

class ReserveRoomActivity : AppCompatActivity(), BuildingListAdapter.OnItemClickListener {
    private lateinit var binding: ActivityRoomReservationMainBinding

    private lateinit var buildingAdapter: BuildingListAdapter
    private lateinit var roomAdapter: RoomDetailAdapter
    private lateinit var buildingRecyclerView: RecyclerView
    private lateinit var roomRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomReservationMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buildingRecyclerView = findViewById(R.id.building_recycler)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        buildingRecyclerView.layoutManager = layoutManager
        val buildings = getBuildingData()
        buildingAdapter = BuildingListAdapter(this, buildings, this)
        buildingRecyclerView.adapter = buildingAdapter

        roomRecyclerView = findViewById(R.id.room_details_recycler)
        roomRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val rooms = getRoomData()
        roomAdapter = RoomDetailAdapter(rooms, "HH")
        roomRecyclerView.adapter = roomAdapter

        val view_for_buildings = binding.viewallBuildings
        val view_for_rooms = binding.viewallRooms

        view_for_buildings.setOnClickListener {
            val intent = Intent(this, BuildingListActivity::class.java)
            startActivity(intent)
        }
//        view_for_rooms.setOnClickListener {
//            val intent = Intent(this, RoomListActivity::class.java )
//            startActivity(intent)
//        }

    }

    override fun onItemClick(building: BuildingDetail) {
        roomAdapter.filterRooms(building)
    }
    private fun getBuildingData(): List<BuildingDetail> {
        return listOf(
            BuildingDetail(1, "HH", 30, R.drawable.building1),
            BuildingDetail(2, "R-13", 20, R.drawable.building2),
            BuildingDetail(3, "Building 105", 15, R.drawable.building3),
        )
    }

    private fun getRoomData(): List<RoomDetail> {
        return listOf(
            RoomDetail(1, 101, "Standard Room", "$100", R.drawable.building1),
            RoomDetail(1, 102, "Standard Room", "$100", R.drawable.building2),
            RoomDetail(2, 103, "Standard Room", "$100", R.drawable.building3),
            RoomDetail(2, 201, "Deluxe Room", "$200", R.drawable.building1),
            RoomDetail(3, 202, "Deluxe Room", "$200", R.drawable.building2),
            RoomDetail(3, 203, "Deluxe Room", "$200", R.drawable.building3),
        )
    }


}
