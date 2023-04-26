package com.miu.housing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.miu.housing.databinding.RoomReserveBinding
import com.miu.housing.db.Booking
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.Room
import com.miu.housing.db.User
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
class RoomActivity : BaseActivity() {
    private lateinit var binding: RoomReserveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RoomReserveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val room = intent.getSerializableExtra("room") as Room

        val roomImage = binding.roomImages
        val buildingName = binding.buildingName
        val roomType = binding.roomType
        val roomPrice = binding.roomPrice

        roomImage.setImageResource(room.image)
        val building_name = intent.getStringExtra("BuildingName").toString()
        buildingName.text = building_name
        roomType.text = room.Room_type
        roomPrice.text = room.price.toString()

        val button = binding.button

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        var user = tmp as User

        button.setOnClickListener{
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to reserve this room?")
                .setPositiveButton("Yes"){dialog,_  ->

                    launch {
                        applicationContext?.let {
                            var newBooking = Booking(building_name,room.id, user.id, true);
                            var savedBooking = MiuHousingDatabase(it).getBookingDao().addBooking(newBooking)
                            if(savedBooking != null){
                                room.status = false
                                MiuHousingDatabase(it).getRoomDao().updateRoom(room)
                                it.toast("Room reserved")



                            }else{
                                it.toast("Error!")
                            }
                        }
                    }
                    dialog.dismiss()
                    val intent = Intent(this, HousingActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)


                }
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