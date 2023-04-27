package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.RoomsListBinding
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.Room
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class RoomListActivity : BaseActivity() {
    private lateinit var binding: RoomsListBinding
    private lateinit var room:List<Room>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = RoomsListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        val recyclerView = findViewById<RecyclerView>(R.id.rooms_list_recycler)
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        launch {
//            applicationContext?.let {
//                room = MiuHousingDatabase(it).getRoomDao().getAllRooms()
//               // val filteredRooms = room.filter { it.buildingName == buildingName }
//                val adapter = RoomDetailAdapter(room,"HH", )
//                recyclerView.adapter = adapter
//            }
//        }





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