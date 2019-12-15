package com.team.animalrescue.rescue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.team.animalrescue.R
import com.team.animalrescue.databinding.RescueFragmentBinding

class RescueScreenFragment : Fragment() {

    private lateinit var binding : RescueFragmentBinding
    private lateinit var viewModel : RescueScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.rescue_fragment,
            container,
            false
            )

        Log.d("viewModel", "ViewModelProviders.of called")
        viewModel = ViewModelProviders.of(this).get(RescueScreenViewModel::class.java)
        return  binding.root
    }

}
