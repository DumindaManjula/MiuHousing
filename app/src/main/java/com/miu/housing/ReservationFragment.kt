package com.miu.housing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miu.housing.databinding.FragmentReservationBinding

class ReservationFragment : Fragment() {
    private lateinit var binding: FragmentReservationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_reservation, container, false)
        binding = FragmentReservationBinding.bind(view)
        return binding.root
    }
}