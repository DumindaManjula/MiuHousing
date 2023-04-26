package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.housing.databinding.ActivityDamageListBinding
import com.miu.housing.db.Damage
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.User
import kotlinx.coroutines.launch

class DamageListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDamageListBinding
    private var damageList: List<Damage> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDamageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        val user = tmp as User

        var rightText = "${user.firstName} ${user.lastName}"
        var leftText = getString(R.string.app_name) + " - Damage List"

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            showCustomActionBar(actionBar, layoutInflater, leftText, rightText)
        }

        var damageListAdapter = DamageListAdapter(damageList)
        binding.rcv.layoutManager = LinearLayoutManager(baseContext)
        binding.rcv.adapter = damageListAdapter

        lifecycleScope.launch{
            baseContext.let {
                damageList = MiuHousingDatabase(it).getDamageDao().getAllDamage()
                if(damageList.size > 0) {
                    damageListAdapter.list = damageList
                    damageListAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}