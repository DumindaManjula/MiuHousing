package com.miu.housing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.view.menu.MenuBuilder
import com.miu.housing.databinding.ActivityComplainBinding
import com.miu.housing.db.Complain
import com.miu.housing.db.MiuHousingDatabase
import com.miu.housing.db.User
import kotlinx.coroutines.launch

class ComplainActivity : BaseActivity() {
    private lateinit var binding: ActivityComplainBinding
    var user: User? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        user = tmp as User

        var rightText = "${user?.firstName} ${user?.lastName}"
        var leftText = getString(com.miu.housing.R.string.app_name) + " - Make Complaint"

        val actionBar: ActionBar? = supportActionBar
        if(actionBar != null) {
            showCustomActionBar(actionBar, layoutInflater, leftText, rightText)
        }
    }
    fun submitComplain(view: View){
        val dmgReason = binding.editTextReason.text

        if (dmgReason.isBlank()) {
            binding.editTextReason.error = "Reason is required"
            binding.editTextReason.requestFocus()
            return
        }

        Toast.makeText(this,"Compliant has been sent.MiuHousing will contact you soon ", Toast.LENGTH_LONG).show()

        val intent = Intent(this, HousingActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)

        var complain = Complain(dmgReason.toString(), user?.emailId)
        launch {
            applicationContext?.let {
                var complain = MiuHousingDatabase(it).getComplainDao().addComplain(complain)
                if(complain == null) {
                    it.toast("There is something wrong")
                } else {
                    it.toast("Your complaint is keep in our system.")
                }
            }
        }
    }
    fun cancelComplain(view: View){
        val intent = Intent(this, HousingActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        menuInflater.inflate(com.miu.housing.R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            com.miu.housing.R.id.gmail -> {
                Toast.makeText(this, item.title.toString(), Toast.LENGTH_SHORT).show()
            }
            com.miu.housing.R.id.logout -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("logout", "logout")
                startActivity(intent)
                //Toast.makeText(this, "Test test", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}