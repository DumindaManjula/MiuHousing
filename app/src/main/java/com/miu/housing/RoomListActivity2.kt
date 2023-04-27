package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.ActivityRoomListBinding
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.Room
import com.miu.housing.db.User
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
class RoomListActivity2 : BaseActivity(){
    private lateinit var binding: ActivityRoomListBinding
    private lateinit var room:List<Room>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val roomRecyclerView = findViewById<RecyclerView>(R.id.check_recycler)
        roomRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val buildingName = intent.getStringExtra("buildingName").toString()

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        var user = tmp as User

        launch {
            applicationContext?.let {
                room = MiuHousingDatabase(it).getRoomDao().getAllRooms()
                val filteredRooms = room.filter { it.buildingName == buildingName }

                val roomAdapter = RoomDetailAdapter(filteredRooms, buildingName, user)
                roomRecyclerView.adapter = roomAdapter
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
}