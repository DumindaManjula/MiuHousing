package com.miu.housing

import android.annotation.SuppressLint
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
import com.miu.housing.db.*
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

        var intent = intent
        var tempLogOut = intent.getStringExtra("logout")
        if(!tempLogOut.equals("logout")){
            initializeSampleData();
        }

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

    @SuppressLint("SuspiciousIndentation")
    private fun initializeSampleData() {

        //User Account data
        val user1 = User("John", "Wick", "john@gmail.com", "123", "A", "613479", 0)
        val user2 = User("Mark", "Taylor", "mark@gmail.com", "123", "A", "613479", 0)
        val user3 = User("Andrew", "Russle", "andrew@gmail.com", "123", "A", "613479", 0)
        val user4 = User("Shane", "Watson", "shane@gmail.com", "123", "A", "613479", 0)
        val user5 = User("Yen", "Truong", "truthblue82@gmail.com", "1111", "A", "613479",0)
        val admin = User("Admin", "Admin", "housing@miu.edu", "1111", "A", "613479",1)

        var building1 = Building("Neptune",2, R.drawable.building1)
        var building2 = Building("Pluto",3, R.drawable.building2)
        var building3 = Building("Earth",3, R.drawable.building3)
        var building4 = Building("Mars",5, R.drawable.building3)


        var room1 = Room("Neptune",100,"Shared",300,true,R.drawable.building1)
        var room2 = Room("Neptune",101,"Single",500,true,R.drawable.building2)
        var room3 = Room("Neptune",102,"Shared",400,true,R.drawable.building1)

        var room4 = Room("Pluto",100,"Shared",200,true,R.drawable.building1)
        var room5 = Room("Pluto",101,"Single",500,true,R.drawable.building2)
        var room6 = Room("Pluto",104,"Shared",600,true,R.drawable.building1)

        var room7 = Room("Earth",110,"Shared",200,true,R.drawable.building1)
        var room8 = Room("Earth",111,"Single",300,true,R.drawable.building1)
        var room9 = Room("Earth",112,"Single",400,true,R.drawable.building1)

        var room10 = Room("Mars",120,"Single",500,true,R.drawable.building1)
        var room11 = Room("Mars",121,"Shared",800,true,R.drawable.building1)
        var room12 = Room("Mars",122,"Single",600,true,R.drawable.building1)


        //Damage Items data
        val damageItem1 = Damage("Table", "Too old", "Not usable", "john@gmail.com")
        val damageItem2 = Damage("Chair", "Not Strong one", "Not usable", "andrew@gmail.com")

        val faq1 = Faq("How to book a room in dorm?", "Go to the MIU website, register a room with housing department.", 3)
        val faq2 = Faq("Student Housing?", "See your options for on-campus housing. Students aligible for off-campus housing can find information about renting a room or apartment in Fairfield, in addition to cost of living resource.", 1)
        val faq3 = Faq("Driving Instructions to Campus Buildings?", "Where to go when you first arrive on campus.", 2)
        val faq4 = Faq("After-hours/late move-in date policies?", "nternational students arriving late may call Campus Security at 641-472-1115", 3)
        val faq5 = Faq("10 things to know before you arrive?", "Climate and clothing, what to bring and not to bring, on-campus telephone / Cable TV / Internet services, insurance, shipping belongings in advance, lodging in Fairfield, and more.", 2)

        val complain1 = Complain("Washed Rooms are not cleand","duminda@miu.edu")
        val complain2 = Complain("Students room is noisy","aven@miu.edu")


//        if (prefs!!.getBoolean("firstrun",true)) {
            launch {
                applicationContext?.let {

                    MiuHousingDatabase(it).getBookingDao().deleteAllBooking()
                    MiuHousingDatabase(it).getBuildingDao().deleteAllBuilding()
                    MiuHousingDatabase(it).getDamageDao().deleteAllDamage()
                    MiuHousingDatabase(it).getRoomDao().deleteAllRoom()
                    MiuHousingDatabase(it).getUserDao().deleteAllUser()
                    MiuHousingDatabase(it).getFaqDao().deleteAllFAQ()
                    MiuHousingDatabase(it).getComplainDao().deleteAllComplain()

                    var user =
                        MiuHousingDatabase(it).getUserDao().addMultipleUsers(user1, user2, user3, user4, user5, admin)
                    if (user != null) {
                        Log.i(MY_MIU_TAG, "Inserted all users at installation.......")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save user data during intialization")
                    }

                    var damage = MiuHousingDatabase(it).getDamageDao()
                        .addMultipleDamages(damageItem1, damageItem2)
                    if (damage != null) {
                        Log.i(MY_MIU_TAG, "Inserted all damages at installation.......")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save damage item during intialization")
                    }

                    var faq = MiuHousingDatabase(it).getFaqDao().addMultipleFaqs(faq1,faq2,faq3,faq4,faq5)
                    if(faq != null) {
                        Log.i(MY_MIU_TAG, "Inserted all faqs at installation...")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save faq item during installation")
                    }

                    var building =
                        MiuHousingDatabase(it).getBuildingDao().addMultipleBuilding(building1, building2, building3, building4)
                    if (building != null) {
                        Log.i(MY_MIU_TAG, "Inserted all building at installation.......")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save building data during intialization")
                    }

                    var room =
                        MiuHousingDatabase(it).getRoomDao().addMultipleRoom(room1, room2, room3, room4, room5, room6, room7, room8, room9,room10, room11, room12 )
                    if (room != null) {
                        Log.i(MY_MIU_TAG, "Inserted all room at installation.......")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save room data during intialization")
                    }

                    var complain =
                        MiuHousingDatabase(it).getComplainDao().addMultipleComplain(complain1, complain2)
                    if (complain != null) {
                        Log.i(MY_MIU_TAG, "Inserted all complain at installation.......")
                    } else {
                        Log.i(MY_MIU_TAG, "Failing to save complain data during intialization")
                    }

                }
            }
//        }

    }
}