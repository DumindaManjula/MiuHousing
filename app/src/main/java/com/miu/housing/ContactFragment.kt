package com.miu.housing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.miu.housing.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_contact, container,false)
        binding = FragmentContactBinding.bind(view)
        return binding.root

        binding.housingEmail1.setOnClickListener{
            Toast.makeText(context,"MiuHousing will contact you soon ", Toast.LENGTH_LONG).show()
        }

        binding.housingEmail2.setOnClickListener{
            Toast.makeText(context,"MiuHousing will contact you soon ", Toast.LENGTH_LONG).show()
        }

        binding.phoneNumber.setOnClickListener{
            Toast.makeText(context,"MiuHousing will contact you soon ", Toast.LENGTH_LONG).show()
        }
    }

}