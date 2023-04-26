package com.miu.housing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.miu.housing.databinding.RoomReturnBinding
import com.miu.housing.db.Booking
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.Room
import com.miu.housing.db.User
import kotlinx.coroutines.launch

class RoomReturnActivity : BaseActivity() {
    private lateinit var binding : RoomReturnBinding
     var room: Room? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= RoomReturnBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var roomImage = binding.roomImages
        var buildingName = binding.buildingName
        var roomNumber = binding.roomNumber
        var roomType = binding.roomType

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        var user = tmp as User

        launch {
            applicationContext?.let {
                var bookingReturn = MiuHousingDatabase(it).getBookingDao().getBooking(user.id)
                room = MiuHousingDatabase(it).getRoomDao().getRoom(bookingReturn.room_number)
                room?.let { it ->
                    roomImage.setImageResource(it.image)
                    roomNumber.text = it.Room_no.toString()
                    roomType.text = it.Room_type
                    buildingName.text= it.buildingName
                }





            }}

        val button = binding.button

        button.setOnClickListener{
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to return this room?")
                .setPositiveButton("Yes"){ dialog,_ ->
                    launch {
                        applicationContext?.let{
                            room?.let { it ->
                            it.status = true}
                            room?.let { it1 -> MiuHousingDatabase(it).getRoomDao().updateRoom(it1) }
                            MiuHousingDatabase(it).getBookingDao().deleteBooking(user.id)
                            it.toast("Room Returned")
                        }
                    }
                    dialog.dismiss()
                    val intent = Intent(this, HousingActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }
                .setNegativeButton("No"){ dialog,_ -> dialog.dismiss()}
                .show()
        }


    }
}