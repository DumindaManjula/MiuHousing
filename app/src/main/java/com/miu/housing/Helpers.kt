package com.miu.housing

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import android.view.LayoutInflater
import android.webkit.WebSettings.TextSize

// With Kotlin extension functions it’s possible to display toasts as simple as this
fun Context.toast(message:String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun Context.showCustomActionBar(actionBar: ActionBar,
                                layoutInflater: LayoutInflater,
                                leftText: String,
                                rightText: String) {

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

        tview.setText(rightText)
        var aview = viewActionBar.findViewById<TextView>(R.id.apptitle)
        aview.setText(leftText)
}