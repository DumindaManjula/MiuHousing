package com.miu.housing

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.miu.housing.databinding.FragmentReservationBinding
import com.miu.housing.db.User

class ReservationFragment(user:User) : Fragment() {
    private lateinit var binding: FragmentReservationBinding
    var userInfo = user
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_reservation, container, false)
        binding = FragmentReservationBinding.bind(view)
        binding.imageButton2.setOnClickListener {

            val intent = Intent(context, ReportDamageActivity::class.java)
            intent.putExtra("userInfo",userInfo) // Here message is a key to retrieve the input text in the second activity
            startActivity(intent)
        }

        binding.imageButton3.setOnClickListener {

            Toast.makeText(context, "to Request a Letter", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, RequestingLetterActivity::class.java)
            intent.putExtra("userInfo",userInfo)
            startActivity(intent)
        }

        return binding.root
    }
}