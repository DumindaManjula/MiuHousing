package com.miu.housing

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.view.menu.MenuBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.housing.data.FaqData
import com.miu.housing.data.ReservationData
import com.miu.housing.databinding.ActivityHousingBinding
import com.miu.housing.db.User

class HousingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHousingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var reservationData = arrayListOf<ReservationData>().apply {
            add(ReservationData(getString(R.string.reserve_room_title), R.drawable.checkin))
            add(ReservationData(getString(R.string.return_room_title), R.drawable.checkout))
            add(ReservationData(getString(R.string.request_letter_title), R.drawable.mailing))
            add(ReservationData(getString(R.string.report_damage_title), R.drawable.repair))
            add(ReservationData(getString(R.string.complain_title), R.drawable.complain))
            add(ReservationData(getString(R.string.emergency_title), R.drawable.emergency))
        }

        var faqData = arrayListOf<FaqData>().apply {
            add(FaqData(1, getString(R.string.faq_q_1), getString(R.string.faq_a_1), 1))
            add(FaqData(2, getString(R.string.faq_q_2), getString(R.string.faq_a_2), 2))
            add(FaqData(3, getString(R.string.faq_q_3), getString(R.string.faq_a_3), 3))
        }

        binding = ActivityHousingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val tmp = intent.getSerializableExtra("user")
        val user = tmp as User
        var fullname = "${user.firstName} ${user.lastName}"
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        var params = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT,
            Gravity.RIGHT
        )
        var viewActionBar = layoutInflater.inflate(R.layout.general_top_header,null)
        actionBar?.setCustomView(viewActionBar, params)
        var tview = viewActionBar.findViewById<TextView>(R.id.loginname)
        tview.setText("Welcome $fullname")
        var appName = getString(R.string.app_name)
        var aview = viewActionBar.findViewById<TextView>(R.id.apptitle)
        aview.setText(appName)

        var housingAdapter = HousingAdapter(this, user, reservationData, faqData)
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
            }
        }

        return super.onOptionsItemSelected(item)
    }
}