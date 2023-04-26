package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.RoomsListBinding

@Suppress("DEPRECATION")
class RoomListActivity : AppCompatActivity() {
    private lateinit var binding: RoomsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = RoomsListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val recyclerView = findViewById<RecyclerView>(R.id.rooms_list_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = RoomDetailAdapter(getRoomData(),"HH")
        recyclerView.adapter = adapter



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