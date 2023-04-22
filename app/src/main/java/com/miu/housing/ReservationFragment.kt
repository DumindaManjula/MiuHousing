package com.miu.housing

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.miu.housing.databinding.FragmentReservationBinding

class ReservationFragment : Fragment() {
    private lateinit var binding: FragmentReservationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_reservation, container, false)
        binding = FragmentReservationBinding.bind(view)
        binding.imageButton2.setOnClickListener {

            Toast.makeText(context, "to Report a Damage", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ReportDamageActivity::class.java)
            intent.putExtra("message","user info") // Here message is a key to retrieve the input text in the second activity
            startActivity(intent)
        }
        return binding.root
    }
}