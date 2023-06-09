package com.miu.housing

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.data.ReservationData
import com.miu.housing.databinding.ReservationViewBinding
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ReservationAdapter(var user: User, var list: ArrayList<ReservationData>): RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>(){
    private lateinit var binding: ReservationViewBinding
    private var rData = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        binding = ReservationViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationViewHolder(binding)
    }

    override fun getItemCount() = rData.size

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.binding.tvTitle.text = rData[position].title
        holder.binding.ivImage.setImageResource(rData[position].image)

        holder.itemView.setOnClickListener {
            Toast.makeText(binding.root.context, "${rData[position].title}", Toast.LENGTH_SHORT).show()
            when(position) {
                0 -> {
                    val intent = Intent(it.context, ReserveRoomActivity::class.java)
                    intent.putExtra("userInfo", user)
                    it.context.startActivity(intent)
                }
                1 -> {
                    GlobalScope.launch {

                        var reservedRoom = MiuHousingDatabase(it.context).getBookingDao().getBooking(user.id)
                        if(reservedRoom !=null){
                            val intent = Intent(it.context, RoomReturnActivity::class.java)
                            intent.putExtra("userInfo", user)
                            it.context.startActivity(intent)
                        }}

                            val builder = AlertDialog.Builder(it.context)
                            builder.setTitle("No Reserved Room Found")
                                .setMessage("You should reserve a room first")
                                .setPositiveButton("Ok"){
                                    dialog,_ ->  dialog.dismiss()
                                }
                                .show()





                }
                2 -> {
                    val intent = Intent(it.context, RequestingLetterActivity::class.java)
                    intent.putExtra("userInfo", user)
                    it.context.startActivity(intent)
                }
                3 -> {
                    val intent = Intent(it.context, ReportDamageActivity::class.java)
                    intent.putExtra("userInfo", user) // Here message is a key to retrieve the input text in the second activity
                    it.context.startActivity(intent)
                }
                4 -> {
                    val intent = Intent(it.context, ComplainActivity::class.java)
                    intent.putExtra("userInfo", user)
                    it.context.startActivity(intent)
                }
                5 -> {
                    val emergencyNumber = "+16414726382"
                    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", emergencyNumber, null))
                    if( intent.resolveActivity(it.context.packageManager) == null){
                        Toast.makeText(binding.root.context, "Device does not allow phone call", Toast.LENGTH_LONG).show()
                    } else {
                        it.context.startActivity(intent)
                    }
                }
                else -> {
                    if(user.isAdmin == 1) {
                        when(position) {
                            6 -> {
                                val intent = Intent(it.context, DamageListActivity::class.java)
                                intent.putExtra("userInfo", user)
                                it.context.startActivity(intent)
                            }
                            7 -> {
                                val intent = Intent(it.context, ComplainListActivity::class.java)
                                intent.putExtra("userInfo", user)
                                it.context.startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
    inner class ReservationViewHolder(val binding: ReservationViewBinding) : RecyclerView.ViewHolder(binding.root)
}