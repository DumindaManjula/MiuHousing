package com.miu.housing

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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

        val adapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, test)
        binding.actv.setAdapter(adapter)
        binding.actv.threshold = 1
        Toast.makeText(this,"This is my message", Toast.LENGTH_LONG).show()

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
        Toast.makeText(this,user.toString(), Toast.LENGTH_LONG).show()

        intent.putExtra("user", user)
        startActivity(intent)

    }
    fun cancelDamage(view: View){

        val intent = Intent(this, HousingActivity::class.java)
        Toast.makeText(this,user.toString(), Toast.LENGTH_LONG).show()

        intent.putExtra("user", user)
        startActivity(intent)
    }

}