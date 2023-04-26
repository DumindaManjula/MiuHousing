package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.housing.databinding.ActivityComplaintListBinding
import com.miu.housing.db.Complaint
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.User
import kotlinx.coroutines.launch

class ComplaintListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComplaintListBinding
    private var complaintList:List<Complaint> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintListBinding.inflate(layoutInflater)
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

        var complaintListAdapter = ComplaintListAdapter(complaintList)
        binding.rcv.layoutManager = LinearLayoutManager(baseContext)
        binding.rcv.adapter = complaintListAdapter

        lifecycleScope.launch{
            baseContext.let{
                complaintList = MiuHousingDatabase(it).getComplaintDao().getAllComplaint()
                if(complaintList.size > 0) {
                    complaintListAdapter.list = complaintList
                    complaintListAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}