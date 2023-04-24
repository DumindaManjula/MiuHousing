package com.miu.housing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.miu.housing.databinding.ActivityComplaintBinding
import com.miu.housing.databinding.ActivityReportDamageBinding
import com.miu.housing.db.User

class ComplaintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComplaintBinding
    var user: User? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        user = tmp as User
    }
    fun submitDamage(view: View){
        val dmgReason = binding.editTextReason.text

        if (dmgReason.isBlank()) {
            binding.editTextReason.error = "Reason required"
            binding.editTextReason.requestFocus()
            return
        }

        Toast.makeText(this,"MiuHousing will contact you soon ", Toast.LENGTH_LONG).show()

        val intent = Intent(this, HousingActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)

    }
    fun cancelDamage(view: View){

        val intent = Intent(this, HousingActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

}