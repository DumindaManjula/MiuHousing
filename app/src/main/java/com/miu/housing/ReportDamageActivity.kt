package com.miu.housing

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.view.menu.MenuBuilder
import com.miu.housing.databinding.ActivityMainBinding
import com.miu.housing.databinding.ActivityReportDamageBinding
import com.miu.housing.db.User

class ReportDamageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportDamageBinding
    var selectedItem: String? = null;
    var user:User? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDamageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var test = arrayOf("Bed","Bed Frame","Mattress","Pillow","Wardrobe","Table","Chair","Drawer","Blinds","Sink")


        val intent = intent
        val tmp = intent.getSerializableExtra("userInfo")
        user = tmp as User

        var fullname = "${user?.firstName} ${user?.lastName}"
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        var params = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT,
            Gravity.RIGHT
        )
        var viewActionBar = layoutInflater.inflate(com.miu.housing.R.layout.general_top_header,null)
        actionBar?.setCustomView(viewActionBar, params)
        var tview = viewActionBar.findViewById<TextView>(com.miu.housing.R.id.loginname)
        tview.setText("$fullname")
        var appName = getString(com.miu.housing.R.string.app_name)
        var aview = viewActionBar.findViewById<TextView>(com.miu.housing.R.id.apptitle)
        aview.setText("$appName-Report Damage")

        val adapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, test)
        binding.actv.setAdapter(adapter)
        binding.actv.threshold = 1

        binding.actv.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this,"Item selected at $position is "
                        + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show()
            }
    }

    fun submitDamage(view: View){

        if (selectedItem == null) {
            binding.actv.error = "Item required"
            binding.actv.requestFocus()
            return
        }

        val dmgReason = binding.editTextReason.text
        val dmgCondition = binding.editTextCondition.text

        if (dmgReason.isBlank()) {
            binding.editTextReason.error = "Reason required"
            binding.editTextReason.requestFocus()
            return
        }

        if (dmgCondition.isBlank()) {
            binding.editTextCondition.error = "Condition required"
            binding.editTextCondition.requestFocus()
            return
        }

        Toast.makeText(this,"Notified damaged Item $selectedItem. MiuHousing will contact you soon ", Toast.LENGTH_LONG).show()

        val intent = Intent(this, HousingActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)

    }
    fun cancelDamage(view: View){

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
                startActivity(intent)
                //Toast.makeText(this, "Test test", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}