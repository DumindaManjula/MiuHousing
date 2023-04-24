package com.miu.housing

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import com.miu.housing.databinding.ActivityRequestingLetterBinding
import com.miu.housing.db.User

class RequestingLetterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestingLetterBinding
    var user: User? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestingLetterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        var viewActionBar = layoutInflater.inflate(R.layout.general_top_header,null)
        actionBar?.setCustomView(viewActionBar, params)
        var tview = viewActionBar.findViewById<TextView>(R.id.loginname)
        tview.setText("Welcome $fullname")
        var appName = getString(R.string.app_name)
        var aview = viewActionBar.findViewById<TextView>(R.id.apptitle)
        aview.setText("$appName-Request Letter")
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

    fun requestLetter(view: View){

        var reason = binding.editTextReason.text

        if (reason.isEmpty()) {
            binding.editTextReason.error = "Reason required"
            binding.editTextReason.requestFocus()
            return
        }

        var link:String? = "housing@miu.edu"

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(link))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Requesting Housing Letter from MIU Housing")
        intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<p>Hi There,</p>"
                +"<p>${reason}</p></small>"
                +"<p>Regards</p></small>"
                +"<p>${user?.firstName} ${user?.lastName}</p>"))
        intent.selector = emailIntent

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intent, "Choose an email client"), 0 )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {

            val intent = Intent(this, HousingActivity::class.java)
            Toast.makeText(this,"Email sent successfully.MIU Housing will contact you soon", Toast.LENGTH_LONG).show()

            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    fun cancelRequest(view: View){

        val intent = Intent(this, HousingActivity::class.java)
        Toast.makeText(this,user.toString(), Toast.LENGTH_LONG).show()

        intent.putExtra("user", user)
        startActivity(intent)
    }
}