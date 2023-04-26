package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import com.miu.housing.databinding.ActivityDamageListBinding
import com.miu.housing.db.User

class DamageListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDamageListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDamageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var damages = arrayOf<List<String>>()
//        var adapter = ArrayAdapter<List<String>>(this, R.layout.activity_damage_list, damages)
//        binding.lview.adapter = adapter

        val intent = intent
        val tmp = intent.getSerializableExtra("user")
        val user = tmp as User

        var rightText = "${user.firstName} ${user.lastName}"
        var leftText = getString(R.string.app_name) + " - Damage List"

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            showCustomActionBar(actionBar, layoutInflater, leftText, rightText)
        }
    }
}