package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.housing.databinding.ActivityComplainListBinding
import com.miu.housing.db.Complain
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.User
import kotlinx.coroutines.launch

class ComplainListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComplainListBinding
    private var complainList:List<Complain> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        val user = tmp as User

        var rightText = "${user.firstName} ${user.lastName}"
        var leftText = getString(R.string.app_name) + " - Complaint List"

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            showCustomActionBar(actionBar, layoutInflater, leftText, rightText)
        }

        var complaintListAdapter = ComplainListAdapter(complainList)
        binding.rcv.layoutManager = LinearLayoutManager(baseContext)
        binding.rcv.adapter = complaintListAdapter

        lifecycleScope.launch{
            baseContext.let{
                complainList = MiuHousingDatabase(it).getComplainDao().getAllComplain()
                if(complainList.size > 0) {
                    complaintListAdapter.list = complainList
                    complaintListAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}