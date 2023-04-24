package com.miu.housing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.data.FaqData
import com.miu.housing.databinding.FaqItemViewBinding
import com.miu.housing.db.User

class FaqAdapter(var faqList: ArrayList<FaqData>) : RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {
    private lateinit var binding : FaqItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        binding = FaqItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FaqViewHolder(binding)
    }

    override fun getItemCount() = faqList.size

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.binding.header.headerText.text = faqList[position].question
        holder.binding.content.contentText.text = faqList[position].answer
    }

    inner class FaqViewHolder(val binding: FaqItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}