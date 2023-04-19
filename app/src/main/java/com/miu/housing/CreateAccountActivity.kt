package com.miu.housing

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.miu.housing.db.User
import com.miu.housing.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }

    fun onCreateAccount(view: View){

        var fn = binding.firstName.text
        var ln = binding.lastName.text
        var mail = binding.email.text
        var pwd = binding.password.text

        if (fn.isNotBlank() && ln.isNotBlank() && mail.isNotBlank() && pwd.isNotBlank()) {
            val user = User(fn.toString(), ln.toString(), mail.toString(), pwd.toString(), "A")

            val data = Intent()
            data.putExtra("newuser", user)
            setResult(Activity.RESULT_OK, data)
            finish()
        }else{
            Toast.makeText(this, "Please enter all field data.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}