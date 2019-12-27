package com.team.animalrescue.homescreen.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.team.animalrescue.rescue.RescueScreenFragment
import com.team.animalrescue.utils.AndroidNavigator

private const val TOTAL_FRAGMENT_COUNT = 4
class HomeViewPagerAdapter(val context: Context, val fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment = RescueScreenFragment.newInstance()
        when(position){
            AndroidNavigator.HomeScreenTabIndex.HOME.tabIndex() -> {
                fragment = RescueScreenFragment.newInstance()
            }
            AndroidNavigator.HomeScreenTabIndex.ADOPTION.tabIndex() -> {
            }
            AndroidNavigator.HomeScreenTabIndex.DONATION.tabIndex() -> {
            }
            AndroidNavigator.HomeScreenTabIndex.MY_ACCOUNT.tabIndex() -> {
            }
        }
        return fragment
    }

    override fun getCount(): Int {
        return TOTAL_FRAGMENT_COUNT
    }
}
