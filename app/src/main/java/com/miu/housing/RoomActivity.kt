package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.miu.housing.databinding.RoomReserveBinding


@Suppress("DEPRECATION")
class RoomActivity : AppCompatActivity() {
    private lateinit var binding: RoomReserveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RoomReserveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val room = intent.getSerializableExtra("room") as RoomDetail

        val roomImage = binding.roomImages
        val buildingName = binding.buildingName
        val roomType = binding.roomType
        val roomPrice = binding.roomPrice

        roomImage.setImageResource(room.image)
        buildingName.text = intent.getStringExtra("BuildingName").toString()
        roomType.text = room.Room_type
        roomPrice.text = room.price

        val button = binding.button

        button.setOnClickListener{
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to reserve this room?")
                .setPositiveButton("Yes"){dialog,_  -> dialog.dismiss()}
                .setNegativeButton("No"){dialog,_  -> dialog.dismiss()}
                .show()
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