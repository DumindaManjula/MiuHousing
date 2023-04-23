package com.miu.housing

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import com.miu.housing.databinding.ActivityReportDamageBinding
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

        this.startActivity(Intent.createChooser(intent, "Choose an email client") )
    }
    fun cancelRequest(view: View){

        val intent = Intent(this, HousingActivity::class.java)
        Toast.makeText(this,user.toString(), Toast.LENGTH_LONG).show()

        intent.putExtra("user", user)
        startActivity(intent)
    }
}