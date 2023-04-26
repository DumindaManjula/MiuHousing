package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.ActivityRoomListBinding


@Suppress("DEPRECATION")
class RoomListActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityRoomListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val roomRecyclerView = findViewById<RecyclerView>(R.id.check_recycler)
        roomRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val buildingId = intent.getIntExtra("building_Id", -1)
        val buildingName= intent.getStringExtra("buildingName").toString()
        val filteredRooms = getRoomData().filter { it.BuildingId == buildingId }

        val roomAdapter = RoomDetailAdapter(filteredRooms, buildingName)
        roomRecyclerView.adapter = roomAdapter
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}