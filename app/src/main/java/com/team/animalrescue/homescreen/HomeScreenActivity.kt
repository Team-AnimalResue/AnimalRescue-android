package com.team.animalrescue.homescreen

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team.animalrescue.R
import com.team.animalrescue.common.BaseActivity
import com.team.animalrescue.common.views.NonSwipeableViewPager
import com.team.animalrescue.databinding.ActivityHomeScreenBinding
import com.team.animalrescue.homescreen.adapter.HomeViewPagerAdapter
import com.team.animalrescue.common.loader.PicassoImageLoader
import com.team.animalrescue.utils.AndroidNavigator
import kotlinx.android.synthetic.main.activity_home_screen.view.*
import kotlinx.android.synthetic.main.primary_toolbar.view.*
import lv.chi.photopicker.ChiliPhotoPicker


class HomeScreenActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)
        setBottomNavigation()
        setupViewpager(binding.viewPager)
        if (savedInstanceState == null) {
            //Toast.makeText(this, "first fragment", Toast.LENGTH_SHORT).show()
            binding.viewPager.currentItem = AndroidNavigator.HomeScreenTabIndex.HOME.tabIndex()
        }

        ChiliPhotoPicker.init(
            loader = PicassoImageLoader(),
            authority = "com.team.animalrescue.fileprovider"
        )

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    AndroidNavigator.HomeScreenTabIndex.HOME.tabIndex() -> setUpTitleToolbar("Rescue")
                    AndroidNavigator.HomeScreenTabIndex.ADOPTION.tabIndex() -> setUpTitleToolbar("Adoption")
                    AndroidNavigator.HomeScreenTabIndex.DONATION.tabIndex() -> setUpTitleToolbar("Donation")
                    AndroidNavigator.HomeScreenTabIndex.MY_ACCOUNT.tabIndex() -> setUpTitleToolbar("My Account")
                }
            }
        })
    }

    private fun setUpTitleToolbar(tabCategories: String) {
        binding.appBarLayout.primary_toolbar.heading.text = tabCategories
    }

    private fun setBottomNavigation() {
        binding.navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun setupViewpager(viewPager: NonSwipeableViewPager) {
        viewPager.adapter = HomeViewPagerAdapter(this, supportFragmentManager)
        viewPager.setPagingEnabled(false)
    }


    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.navigation_rescue -> {
                //Toast.makeText(this, "first fragment", Toast.LENGTH_SHORT).show()
                binding.viewPager.currentItem =
                    AndroidNavigator.HomeScreenTabIndex.HOME.tabIndex()
                return true
            }
            R.id.navigation_adopt -> {
                binding.viewPager.currentItem =
                    AndroidNavigator.HomeScreenTabIndex.ADOPTION.tabIndex()
                return true
            }
            R.id.navigation_donate -> {
                binding.viewPager.currentItem =
                    AndroidNavigator.HomeScreenTabIndex.DONATION.tabIndex()
                return true
            }
            R.id.navigation_user -> {
                binding.viewPager.currentItem =
                    AndroidNavigator.HomeScreenTabIndex.MY_ACCOUNT.tabIndex()
                return true
            }
        }
        return false
    }


}
