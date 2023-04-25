package com.miu.housing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.housing.data.FaqData
import com.miu.housing.databinding.FragmentFaqBinding

class FaqFragment(faqData: ArrayList<FaqData>) : Fragment() {
    private lateinit var binding: FragmentFaqBinding
    var fData = faqData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_faq, container, false)
        binding = FragmentFaqBinding.bind(view)
        binding.rcv.layoutManager = LinearLayoutManager(context)
        binding.rcv.adapter = FaqAdapter(fData)

        return binding.root
    }

    fun showAddFAQForm(view: View) {
        //
    }
}