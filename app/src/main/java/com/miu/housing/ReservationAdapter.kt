package com.miu.housing

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.data.ReservationData
import com.miu.housing.databinding.ReservationViewBinding
import com.miu.housing.db.User

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
                0 -> {}
                1 -> {}
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
                4 -> {}
                5 -> {}
            }
        }
    }
    inner class ReservationViewHolder(val binding: ReservationViewBinding) : RecyclerView.ViewHolder(binding.root)
}