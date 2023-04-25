package com.miu.housing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.housing.data.FaqData
import com.miu.housing.databinding.FragmentFaqBinding

class FaqFragment(faqData: ArrayList<FaqData>) : Fragment(){
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
        binding.fab.setOnClickListener {
            var builder = AlertDialog.Builder(view.context)
            builder.setTitle(R.string.faq_dialog_add_title)
                .setView(R.layout.dialog_faq)
                .setNegativeButton(R.string.dialog_nev_btn) { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .setPositiveButton(R.string.dialog_pos_btn) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
            var dialog: AlertDialog = builder.create()
            dialog.show()
        }
        return binding.root
    }
}