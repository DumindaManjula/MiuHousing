package com.miu.housing

import android.content.Intent
import android.view.View


class BuildingCardListener {
    companion object: View.OnClickListener{
        override fun onClick(v: View) {
            val context = v.context
            val intent = Intent(context, BuildingListActivity::class.java)
            context.startActivity(intent)
        }
        fun getInstance(): View.OnClickListener {
            return this
        }

    }
}