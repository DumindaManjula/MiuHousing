package com.miu.housing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.ActivityRoomReservationMainBinding
import com.miu.housing.db.Building
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.Room
import com.miu.housing.db.User
import kotlinx.coroutines.launch
import java.util.*

@Suppress("DEPRECATION")
class ReserveRoomActivity : BaseActivity(), BuildingListAdapter.OnItemClickListener {
    private lateinit var binding: ActivityRoomReservationMainBinding

    private lateinit var buildingAdapter: BuildingListAdapter
    private lateinit var roomAdapter: RoomDetailAdapter
    private lateinit var buildingRecyclerView: RecyclerView
    private lateinit var roomRecyclerView: RecyclerView
    private lateinit var buildings : List<Building>
    private lateinit var room:List<Room>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomReservationMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buildingRecyclerView = findViewById(R.id.building_recycler)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        buildingRecyclerView.layoutManager = layoutManager

        roomRecyclerView = findViewById(R.id.room_details_recycler)
        roomRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        var user = tmp as User

        launch {
            applicationContext?.let {

                    buildings = MiuHousingDatabase(it).getBuildingDao().getAllBuildings()
                   room = MiuHousingDatabase(it).getRoomDao().getAllRooms()

                buildingAdapter = BuildingListAdapter(applicationContext, buildings, user)
                buildingRecyclerView.adapter = buildingAdapter

                roomAdapter = RoomDetailAdapter(room, "HH",user)
                roomRecyclerView.adapter = roomAdapter

            }
        }





        val view_for_buildings = binding.viewallBuildings
        view_for_buildings.setOnClickListener {
            val intent = Intent(this, BuildingListActivity::class.java)
            intent.putExtra("userInfo", user)
            startActivity(intent)
        }


    }

    override fun onItemClick(building: Building) {
        roomAdapter.filterRooms(building)
    }




}
