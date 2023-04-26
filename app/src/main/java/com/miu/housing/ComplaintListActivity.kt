package com.miu.housing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.miu.housing.db.User

class ComplaintListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_list)

        val intent = intent
        val tmp = intent.getSerializableExtra("user")
        val user = tmp as User

        var rightText = "${user.firstName} ${user.lastName}"
        var leftText = getString(R.string.app_name)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            showCustomActionBar(actionBar, layoutInflater, leftText, rightText)
        }
    }
}