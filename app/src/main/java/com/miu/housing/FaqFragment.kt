package com.miu.housing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miu.housing.databinding.FragmentFaqBinding

class FaqFragment : Fragment() {
    private lateinit var binding: FragmentFaqBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_faq, container, false)
        binding = FragmentFaqBinding.bind(view)
        return binding.root
    }
}