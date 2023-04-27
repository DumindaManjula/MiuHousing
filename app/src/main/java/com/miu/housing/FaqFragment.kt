package com.miu.housing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.housing.databinding.FragmentFaqBinding
import com.miu.housing.db.Faq
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.User
import kotlinx.coroutines.launch

class FaqFragment(var user: User) : Fragment(){
    private lateinit var binding: FragmentFaqBinding
    private var dialog: AlertDialog? = null
    private var fData = emptyList<Faq>()
    private var fAdapter = FaqAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_faq, container, false)
        binding = FragmentFaqBinding.bind(view)
        binding.rcv.layoutManager = LinearLayoutManager(context)

        //check logged in user is admin and show FAB button to add new FAQ
        if(user.isAdmin == 0 || user.isAdmin == null) {
            binding.fab.visibility = View.INVISIBLE
        }
        //fAdapter.loginUser = user
        binding.rcv.adapter = fAdapter
        binding.fab.setOnClickListener {
            var builder = AlertDialog.Builder(view.context)
            builder.setTitle(R.string.faq_dialog_add_title)
                .setView(R.layout.dialog_faq)
                .setNegativeButton(R.string.dialog_nev_btn, null)
                .setPositiveButton(R.string.dialog_pos_btn, null)

            dialog = builder.create()
            dialog!!.setOnShowListener {
                val posBtn: Button = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                posBtn.setOnClickListener(View.OnClickListener {
                    onCreateFAQ()
                })
            }
            dialog!!.show()
        }

        lifecycleScope.launch {
            context?.let {
                fData = MiuHousingDatabase(it).getFaqDao().getAllFaqs()
                if(fData.size > 0) {
                    fAdapter.faqList = fData
                    fAdapter.notifyDataSetChanged()
                }
            }
        }
        return binding.root
    }

    private fun onCreateFAQ() {
        if(checkFAQDialog()) {
            var question = dialog?.findViewById<EditText>(R.id.etQuestion)?.text.toString()
            var answer = dialog?.findViewById<EditText>(R.id.etAnswer)?.text.toString()
            var radioGroup = dialog?.findViewById<RadioGroup>(R.id.radioGroup)
            var selectedLevel = radioGroup?.checkedRadioButtonId
            var selectedRadio = selectedLevel?.let { dialog?.findViewById<RadioButton>(it) }

            var level: Int?
            when(selectedRadio?.text.toString()) {
                getString(R.string.faq_level_high) -> { level = 3}
                getString(R.string.faq_level_medium) -> { level = 2}
                getString(R.string.faq_level_normal) -> { level = 1}
                else -> level = 1
            }

            var faq = Faq(question, answer, level)
            lifecycleScope.launch {
                context?.let {
                    MiuHousingDatabase(it).getFaqDao().addFaq(faq)
                    fData = MiuHousingDatabase(it).getFaqDao().getAllFaqs()
                    fAdapter.faqList = fData
                    fAdapter.notifyDataSetChanged()
                    it.toast("A FAQ is created")
                }
            }

            dialog?.dismiss()
        } else {
            context?.toast("FAQ form is invalid!")
        }
    }
    private fun checkFAQDialog():Boolean {
        var flag: Boolean = true
        var etQuestion = dialog?.findViewById<EditText>(R.id.etQuestion)
        var etAnswer = dialog?.findViewById<EditText>(R.id.etAnswer)

        if(etQuestion?.text.toString() == "") {
            etQuestion?.setError("Question is required!")
            flag = false
        }
        if(etAnswer?.text.toString() == "") {
            etAnswer?.setError("Answer is required!")
            flag = false
        }
        return flag
    }
}