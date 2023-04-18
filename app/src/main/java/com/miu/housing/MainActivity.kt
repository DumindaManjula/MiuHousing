package com.miu.housing

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.miu.housing.db.User
import com.miu.housing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userOb: ArrayList<User>
    private lateinit var binding: ActivityMainBinding

    val MY_MIU_TAG = "MiuHousingApp"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        userOb = ArrayList<User>()
        userOb.add(User("John","Wick","john@gmail.com","123"))
        userOb.add(User("Andrew","Smith","andrew@gmail.com","345"))
        userOb.add(User("Ryan","James","Ryan@gmail.com","567"))
        userOb.add(User("Mark","Boucher","mark@gmail.com","789"))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == Activity.RESULT_OK) {
                val newUser = result.data?.getSerializableExtra("newuser") as User
                if (newUser != null) {
                    userOb.add(newUser)
                }
            }
            else
                Toast.makeText(this, "Failed to get Result.", Toast.LENGTH_LONG).show()
        }

        binding.btnCreateNewAccount.setOnClickListener {
            var intent = Intent(this,CreateAccountActivity::class.java)
            resultContracts.launch(intent)
        }
    }

    fun onSignIn(view: View){

        var email = binding.etEmailAddress.text.trim().toString();
        var password = binding.etPassword.text.trim().toString();
        var isUserValid = false;
        var confirmedUser: User? = null

        for(user in userOb){
            if(email == user.emailId && password == user.password){
                Log.i(MY_MIU_TAG, "Confirmed User")
                confirmedUser = user;
                isUserValid = true
                break;
            }
        }
        if(isUserValid){
            val intent = Intent(this@MainActivity, HousingActivity::class.java)
            intent.putExtra("user", confirmedUser)
            startActivity(intent)
        }else{
            Log.i(MY_MIU_TAG, "Invalid User")
            Toast.makeText(this, "Incorrect username or password.", Toast.LENGTH_LONG).show()
        }

    }

}