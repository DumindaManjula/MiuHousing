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
import com.miu.housing.db.MiuHousingDatabase
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    val MY_MIU_TAG = "MiuHousingApp"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

//        userOb = ArrayList<User>()
//        userOb.add(User("John","Wick","john@gmail.com","123", "A"))
//        userOb.add(User("Andrew","Smith","andrew@gmail.com","345", "A"))
//        userOb.add(User("Ryan","James","Ryan@gmail.com","567", "A"))
//        userOb.add(User("Mark","Boucher","mark@gmail.com","789", "A"))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "User saved successfully.", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this, "Failed to save user.", Toast.LENGTH_LONG).show()
        }

        binding.btnCreateNewAccount.setOnClickListener {
            var intent = Intent(this,CreateAccountActivity::class.java)
            resultContracts.launch(intent)
        }
    }

    fun onSignIn(view: View){

        var email = binding.etEmailAddress.text.trim().toString();
        var password = binding.etPassword.text.trim().toString();

        var confirmedUser: User? = null

        if(email.isEmpty()){
            binding.etEmailAddress.error = "Email Required"
            binding.etEmailAddress.requestFocus()
            return
        }

        if(password.isEmpty()){
            binding.etPassword.error = "Password Required"
            binding.etPassword.requestFocus()
            return
        }

        launch {
            applicationContext?.let {

                var isUserValid = false;
                var allUsers:List<User> = MiuHousingDatabase(it).getUserDao().getAllUsers()
                for(user in allUsers){
                    Log.i(MY_MIU_TAG, "email :" + email +" "+ user.emailId)
                    Log.i(MY_MIU_TAG, "password :" + password +" "+ user.password)
                    if(email == user.emailId && password == user.password){
                        Log.i(MY_MIU_TAG, "Confirmed User")
                        confirmedUser = user;
                        isUserValid = true
                        break;
                    }
                    Log.i(MY_MIU_TAG, "test" + user.toString())
                }

                //        Log.i(MY_MIU_TAG, "isUserValid" + isUserValid)
                if(isUserValid){
                    val intent = Intent(this@MainActivity, HousingActivity::class.java)
                    intent.putExtra("user", confirmedUser)
                    startActivity(intent)
                }else{
                    Log.i(MY_MIU_TAG, "Invalid User")
                    it.toast("Incorrect username or password.")
                }
            }
        }
    }
}