package com.miu.housing

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.housing.databinding.ActivityHousingBinding

class HousingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHousingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHousingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var housingAdapter = HousingAdapter(this)
        binding.vpager.adapter = housingAdapter
        binding.tlayout.tabGravity = TabLayout.GRAVITY_FILL

        TabLayoutMediator(binding.tlayout, binding.vpager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = getString(R.string.home_tab)
                    tab.setIcon(R.drawable.baseline_home_24)
                }
                1 -> {
                    tab.text = getString(R.string.reservation_tab)
                    tab.setIcon(R.drawable.booking)
                }
                2 -> {
                    tab.text = getString(R.string.contact_tab)
                    tab.setIcon(R.drawable.baseline_perm_contact_calendar_24)
                }
                3 -> {
                    tab.text = getString(R.string.faq_tab)
                    tab.setIcon(R.drawable.baseline_question_answer_24)
                }
            }
        }.attach()
    }
    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.gmail -> {
                Toast.makeText(this, item.title.toString(), Toast.LENGTH_SHORT).show()
            }
            R.id.logout -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //Toast.makeText(this, "Test test", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}