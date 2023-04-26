package com.miu.housing

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.miu.housing.databinding.ActivityMainBinding
import com.miu.housing.db.Damage
import com.miu.housing.db.Faq
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.User
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    val MY_MIU_TAG = "MiuHousingApp"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);

        //initializeSampleData();

        var resultContracts =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if(result.resultCode == Activity.RESULT_OK) {
//                Toast.makeText(this, "User saved successfully.", Toast.LENGTH_LONG).show()
//            }
//            else
//                Toast.makeText(this, "Failed to save user.", Toast.LENGTH_LONG).show()
            }

        binding.btnCreateNewAccount.setOnClickListener {
            var intent = Intent(this, CreateAccountActivity::class.java)
            resultContracts.launch(intent)
        }

        binding.tvForgotPassword.setOnClickListener {
            if (binding.etEmailAddress.text.toString().isNotEmpty()) {
                var email = binding.etEmailAddress.text.toString()
                if (!isVaidEmail(email)) {
                    binding.etEmailAddress.error = "Email is invalid!"
                } else {
                    launch {
                        applicationContext?.let {
                            var user: User? =
                                MiuHousingDatabase(it).getUserDao().getUserByEmailId(email)
                            user?.let {
                                var eBody = "Your password is: ${it.password}"
                                composeEmail(
                                    email,
                                    "Forgot password for email $email from MIU Housing",
                                    eBody
                                )
                            }
                            if (user == null) {
                                toast("Can not find user with email ${email}")
                            }
                        }
                    }
                }
            } else {
                toast("Email address must not be empty.")
            }
        }
    }

    private fun composeEmail(address: String, subject: String, content: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, content)
            intent.type = "text/plain"
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun onSignIn(view: View) {
        if (checkSignInForm()) {
            var email = binding.etEmailAddress.text.trim().toString()
            var password = binding.etPassword.text.trim().toString()
            if (checkSignInForm()) {
                launch {
                    applicationContext?.let {
                        var loginUser: User? =
                            MiuHousingDatabase(it).getUserDao().getUserByEmailId(email)
                        loginUser?.let {
                            if (password == loginUser?.password) {
                                Log.i(MY_MIU_TAG, "Confirmed User")
                                Log.i(MY_MIU_TAG, "test" + loginUser.toString())
                                val intent = Intent(this@MainActivity, HousingActivity::class.java)
                                intent.putExtra("user", loginUser)
                                startActivity(intent)
                            } else {
                                binding.etPassword.error = "Password is not correct!"
                            }
                        }
                        if (loginUser == null) {
                            toast("Can not find user with email ${email}")
                        }
                    }
                }
            }
        }
    }

    private fun checkSignInForm(): Boolean {
        var flag: Boolean = true
        if (binding.etEmailAddress.text.isEmpty()) {
            binding.etEmailAddress.error = "Email is required!"
            flag = false
        } else if (!isVaidEmail(binding.etEmailAddress.text.toString())) {
            binding.etEmailAddress.error = "Email is invalid!"
            flag = true
        }
        if (binding.etPassword.text.isEmpty()) {
            binding.etPassword.error = "Password is required!"
            flag = false
        }
        return flag
    }

    private fun isVaidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }

    private fun initializeSampleData() {

        //User Account data
        val user1 = User("John", "Wick", "john@gmail.com", "123", "A")
        val user2 = User("Mark", "Taylor", "mark@gmail.com", "123", "A")
        val user3 = User("Andrew", "Russle", "andrew@gmail.com", "123", "A")
        val user4 = User("Shane", "Watson", "shane@gmail.com", "123", "A")
        val admin = User("Admin", "Admin", "housing@miu.edu", "1111", "A", 1)

        //Damage Items data
        val damageItem1 = Damage("Table", "Too old", "Not usable")
        val damageItem2 = Damage("Chair", "Not Strong one", "Not usable")

        val faq1 = Faq("How to book a room in dorm?", "Go to the MIU website, register a room with housing department.", 3)
        val faq2 = Faq("Student Housing?", "See your options for on-campus housing. Students aligible for off-campus housing can find information about renting a room or apartment in Fairfield, in addition to cost of living resource.", 1)
        val faq3 = Faq("Driving Instructions to Campus Buildings?", "Where to go when you first arrive on campus.", 2)
        val faq4 = Faq("After-hours/late move-in date policies?", "nternational students arriving late may call Campus Security at 641-472-1115", 3)
        val faq5 = Faq("10 things to know before you arrive?", "Climate and clothing, what to bring and not to bring, on-campus telephone / Cable TV / Internet services, insurance, shipping belongings in advance, lodging in Fairfield, and more.", 2)

//        if (prefs!!.getBoolean("firstrun",true)) {
            launch {
                applicationContext?.let {
                    MiuHousingDatabase(it).getUserDao().deleteAll()
                    var user =
                        MiuHousingDatabase(it).getUserDao().addMultipleUsers(user1, user2, user3, user4, admin)
                    if (user != null) {
                        Log.i(MY_MIU_TAG, "Inserted all users at installation.......")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save user data during intialization")
                    }

                    MiuHousingDatabase(it).getDamageDao().deleteAll()
                    var damage = MiuHousingDatabase(it).getDamageDao()
                        .addMultipleDamages(damageItem1, damageItem2)
                    if (damage != null) {
                        Log.i(MY_MIU_TAG, "Inserted all damages at installation.......")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save damage item during intialization")
                    }

                    MiuHousingDatabase(it).getFaqDao().deleteAll()
                    var faq = MiuHousingDatabase(it).getFaqDao().addMultipleFaqs(faq1,faq2,faq3,faq4,faq5)
                    if(faq != null) {
                        Log.i(MY_MIU_TAG, "Inserted all faqs at installation...")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save faq item during installation")
                    }
                }
            }
//        }

    }
}