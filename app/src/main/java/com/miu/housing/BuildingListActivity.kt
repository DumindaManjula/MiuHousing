package com.miu.housing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.GridLayoutBuildingsBinding


@Suppress("DEPRECATION")
class BuildingListActivity : AppCompatActivity(), BuildingListAdapter.OnItemClickListener  {
    private lateinit var binding: GridLayoutBuildingsBinding

    private lateinit var buildingAdapter: BuildingListAdapter
    private lateinit var roomAdapter: RoomDetailAdapter
    private lateinit var buildingRecyclerView: RecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        binding = GridLayoutBuildingsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


       buildingRecyclerView = findViewById(R.id.building_list_recycler)
        buildingRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



       val buildings = getBuildingData()
        buildingAdapter = BuildingListAdapter(this, buildings, this)
        buildingRecyclerView.adapter = buildingAdapter




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