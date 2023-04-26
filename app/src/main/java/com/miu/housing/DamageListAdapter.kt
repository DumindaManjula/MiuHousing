package com.miu.housing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.DamageListBinding
import com.miu.housing.db.Damage

class DamageListAdapter(var list: ArrayList<Damage>): RecyclerView.Adapter<DamageListAdapter.DamageListViewHolder>() {
    private lateinit var binding: DamageListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DamageListViewHolder {
        binding = DamageListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DamageListViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DamageListViewHolder, position: Int) {
        //
    }
    inner class DamageListViewHolder(val binding: DamageListBinding): RecyclerView.ViewHolder(binding.root)
}