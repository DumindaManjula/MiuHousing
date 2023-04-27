package com.miu.housing

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.GridLayoutBuildingsBinding
import com.miu.housing.db.Building
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.User
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
class BuildingListActivity : BaseActivity(), BuildingListAdapter.OnItemClickListener  {
    private lateinit var binding: GridLayoutBuildingsBinding

    private lateinit var buildingAdapter: BuildingListAdapter
    private lateinit var roomAdapter: RoomDetailAdapter
    private lateinit var buildingRecyclerView: RecyclerView
    private lateinit var buildings : List<Building>




    override fun onCreate(savedInstanceState: Bundle?) {
        binding = GridLayoutBuildingsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


       buildingRecyclerView = findViewById(R.id.building_list_recycler)
        buildingRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        var user = tmp as User


        launch {
            applicationContext?.let {
                buildings = MiuHousingDatabase(it).getBuildingDao().getAllBuildings()
                buildingAdapter = BuildingListAdapter(applicationContext, buildings, user)
                buildingRecyclerView.adapter = buildingAdapter
            }
        }






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

    override fun onItemClick(building: Building) {
           roomAdapter.filterRooms(building)
    }


}