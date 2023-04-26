package com.miu.housing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.DamageItemBinding
import com.miu.housing.db.Damage

class DamageListAdapter(var list: List<Damage>): RecyclerView.Adapter<DamageListAdapter.DamageListViewHolder>() {
    private lateinit var binding: DamageItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DamageListViewHolder {
        binding = DamageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DamageListViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DamageListViewHolder, position: Int) {
        holder.binding.tvName.text = list[position].itemName
        holder.binding.tvReason.text = list[position].reason
        holder.binding.tvCondition.text = list[position].condition
    }
    inner class DamageListViewHolder(val binding: DamageItemBinding): RecyclerView.ViewHolder(binding.root)
}