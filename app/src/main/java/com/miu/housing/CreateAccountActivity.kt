package com.miu.housing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.miu.housing.db.User
import com.miu.housing.databinding.ActivityCreateAccountBinding
import com.miu.housing.db.MiuHousingDatabase
import kotlinx.coroutines.launch

class CreateAccountActivity : BaseActivity() {

    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }

    fun onCreateAccount(view: View) {

        var fn = binding.firstName.text
        var ln = binding.lastName.text
        var mail = binding.email.text
        var pwd = binding.password.text

        if (fn.isEmpty()) {
            binding.firstName.error = "Firstname Required"
            //binding.firstName.requestFocus()
        }

        if (ln.isEmpty()) {
            binding.lastName.error = "Lastname Required"
            //binding.lastName.requestFocus()
        }

        if (mail.isEmpty()) {
            binding.email.error = "Email Required"
            //binding.email.requestFocus()
        }

        if (pwd.isEmpty()) {
            binding.password.error = "Password Required"
            //binding.password.requestFocus()
        }

        if (!isValidEmail(mail.toString())) {
            binding.email.error = "Enter valid Email"
            //binding.email.requestFocus()
        }

        val user = User(fn.toString(), ln.toString(), mail.toString(), pwd.toString(), "A")

        launch {
            applicationContext?.let {
                var userEmail: User? =
                    MiuHousingDatabase(it).getUserDao().getUserByEmailId(mail.toString())
                if (userEmail != null) {
                    it.toast("Already have an account with this email address")
                } else {
                    var user = MiuHousingDatabase(it).getUserDao().addUser(user)
                    if(user != null){
                        it.toast("User Created")
                        val data = Intent()
                        setResult(Activity.RESULT_OK, data)
                        finish()
                    }else{
                        it.toast("Failing to save user data")
                    }
                }
            }
        }

    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }
}