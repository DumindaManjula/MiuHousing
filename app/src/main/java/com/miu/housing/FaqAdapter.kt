package com.miu.housing

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.miu.housing.databinding.FaqItemViewBinding
import com.miu.housing.db.Faq
import com.miu.housing.db.MiuHousingDatabase
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class FaqAdapter() : RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {
    private lateinit var binding : FaqItemViewBinding
    var faqList: List<Faq> = emptyList()
    var context: Context? = null
    var selectedFaq: Faq? = null
    //private var dialog: AlertDialog? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        context = parent.context
        binding = FaqItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FaqViewHolder(binding)
    }

    override fun getItemCount() = faqList.size

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.binding.header.headerText.text = faqList[position].question
        holder.binding.content.contentText.text = faqList[position].answer
        var headerColor:Int = R.color.info_header
        var bodyColor:Int = R.color.info_body
        when(faqList[position].level) {
            1 -> {
                headerColor = R.color.info_header
                bodyColor = R.color.info_body
            }
            2 -> {
                headerColor = R.color.warning_header
                bodyColor = R.color.warning_body
            }
            3 -> {
                headerColor = R.color.danger_header
                bodyColor = R.color.danger_body
            }
        }
        holder.binding.header.header.setBackgroundResource(headerColor)
        holder.binding.content.content.setBackgroundResource(bodyColor)
//        holder.binding.header.ibEdit.setOnClickListener {
//            context?.toast("edit")
//        }
//        holder.binding.header.ibDelete.setOnClickListener {
//            context?.toast("delete")
//        }
    }

//    private fun checkFAQDialog():Boolean {
//        var flag: Boolean = true
//        var etQuestion = dialog?.findViewById<EditText>(R.id.etQuestion)
//        var etAnswer = dialog?.findViewById<EditText>(R.id.etAnswer)
//
//        if(etQuestion?.text.toString() == "") {
//            etQuestion?.setError("Question is required!")
//            flag = false
//        }
//        if(etAnswer?.text.toString() == "") {
//            etAnswer?.setError("Answer is required!")
//            flag = false
//        }
//        return flag
//    }

//    private fun initDialog(view: View) {
//        var builder = AlertDialog.Builder(view.context)
//        builder.setTitle(R.string.faq_dialog_edit_title)
//            .setView(R.layout.dialog_faq)
//            .setNegativeButton(R.string.dialog_nev_btn, null)
//            .setPositiveButton(R.string.dialog_pos_btn, null)
//
//        dialog = builder.create()
//        dialog!!.setOnShowListener {
//            val posBtn: Button = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
//            posBtn.setOnClickListener(View.OnClickListener {
//                onUpdateFAQ()
//            })
//        }
//        dialog!!.show()
//    }
//    private fun onUpdateFAQ() {
//        if(checkFAQDialog()) {
//            var question = dialog?.findViewById<EditText>(R.id.etQuestion)?.text.toString()
//            var answer = dialog?.findViewById<EditText>(R.id.etAnswer)?.text.toString()
//            var radioGroup = dialog?.findViewById<RadioGroup>(R.id.radioGroup)
//            var selectedLevel = radioGroup?.checkedRadioButtonId
//            var selectedRadio = selectedLevel?.let { dialog?.findViewById<RadioButton>(it) }
//
//            var level: Int?
//            when(selectedRadio?.text.toString()) {
//                getString(R.string.faq_level_high) -> { level = 3}
//                getString(R.string.faq_level_medium) -> { level = 2}
//                getString(R.string.faq_level_normal) -> { level = 1}
//                else -> level = 1
//            }
//
//            var faq = Faq(question, answer, level)
//            lifecycleScope.launch {
//                context?.let {
//                    MiuHousingDatabase(it).getFaqDao().addFaq(faq)
//                    var fData = MiuHousingDatabase(it).getFaqDao().getAllFaqs()
//                    faqList = fData
//                    this@FaqAdapter.notifyDataSetChanged()
//                    it.toast("A FAQ is updated")
//                }
//            }
//
//            dialog?.dismiss()
//        } else {
//            context?.toast("FAQ form is invalid!")
//        }
//    }

    inner class FaqViewHolder(val binding: FaqItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}