package com.miu.housing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.miu.housing.data.ReservationData
import com.miu.housing.databinding.FragmentReservationBinding
import com.miu.housing.db.User

class ReservationFragment(user: User, rData: ArrayList<ReservationData>) : Fragment() {
    private lateinit var binding: FragmentReservationBinding
    var userInfo = user
    var reservationData = rData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_reservation, container, false)
        binding = FragmentReservationBinding.bind(view)
        binding.rcv.layoutManager = GridLayoutManager(context, 2)
        binding.rcv.adapter = ReservationAdapter(userInfo, reservationData)

        return binding.root
    }
}