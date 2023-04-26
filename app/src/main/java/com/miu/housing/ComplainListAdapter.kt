package com.miu.housing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.ComplainItemBinding
import com.miu.housing.db.Complain

class ComplainListAdapter(var list: List<Complain>): RecyclerView.Adapter<ComplainListAdapter.ComplainListViewHolder>() {
    private lateinit var binding: ComplainItemBinding
    inner class ComplainListViewHolder(val binding: ComplainItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplainListViewHolder {
        binding = ComplainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComplainListViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ComplainListViewHolder, position: Int) {
        holder.binding.tvContent.text = list[position].content
        holder.binding.tvEmail.text = list[position].email
    }
}