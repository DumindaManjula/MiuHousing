package com.miu.housing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.data.ReservationData
import com.miu.housing.databinding.RowDataBinding

class ReservationAdapter(var list: ArrayList<ReservationData>): RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>(){
    private lateinit var binding: RowDataBinding
    private var rData = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        binding = RowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationViewHolder(binding)
    }

    override fun getItemCount() = rData.size

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.binding.tvTitle.text = rData[position].title
        holder.binding.ivImage.setImageResource(rData[position].image)
    }
    inner class ReservationViewHolder(val binding: RowDataBinding) : RecyclerView.ViewHolder(binding.root)
}