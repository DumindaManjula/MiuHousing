package com.miu.housing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.ComplaintItemBinding
import com.miu.housing.db.Complaint

class ComplaintListAdapter(var list: List<Complaint>): RecyclerView.Adapter<ComplaintListAdapter.ComplaintListViewHolder>() {
    private lateinit var binding: ComplaintItemBinding
    inner class ComplaintListViewHolder(val binding: ComplaintItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintListViewHolder {
        binding = ComplaintItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComplaintListViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ComplaintListViewHolder, position: Int) {
        holder.binding.tvContent.text = list[position].content
        holder.binding.tvEmail.text = list[position].email
    }
}