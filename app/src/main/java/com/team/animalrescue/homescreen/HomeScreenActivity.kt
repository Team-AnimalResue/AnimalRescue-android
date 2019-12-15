package com.team.animalrescue.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.team.animalrescue.R
import com.team.animalrescue.common.BaseActivity
import com.team.animalrescue.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : BaseActivity() {

    lateinit var binding : ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home_screen)

        setBottomNavigation()
    }

    private fun setBottomNavigation() {

    }


}
