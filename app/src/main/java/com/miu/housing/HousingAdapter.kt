package com.miu.housing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miu.housing.data.FaqData
import com.miu.housing.data.ReservationData
import com.miu.housing.db.User

class HousingAdapter(fragmentActivity: FragmentActivity,
                     user: User,
                     rData: ArrayList<ReservationData>,
                     faqData: ArrayList<FaqData>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 4
    var userInfo = user
    var reservationData = rData
    var fData = faqData

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> HomeFragment()
            1 -> ReservationFragment(userInfo, reservationData)
            2 -> ContactFragment()
            3 -> FaqFragment(fData)
            else -> Fragment()
        }
    }

}