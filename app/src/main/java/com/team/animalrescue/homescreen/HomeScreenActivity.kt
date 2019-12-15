package com.team.animalrescue.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team.animalrescue.R
import com.team.animalrescue.common.BaseActivity
import com.team.animalrescue.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var binding : ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home_screen)

        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.navigation_rescue -> {

                return true
            }
            R.id.navigation_adopt -> {
                return true
            }
            R.id.navigation_donate -> {
                return true
            }
            R.id.navigation_user -> {
                return true
            }
            else ->return false
        }
    }


}
