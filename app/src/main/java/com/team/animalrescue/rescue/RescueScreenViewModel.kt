package com.team.animalrescue.rescue

import android.util.Log
import androidx.lifecycle.ViewModel

class RescueScreenViewModel : ViewModel() {
    init {
        Log.d("viewModel", "Rescue Screen View Model created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("viewModel", "Rescue Screen View Model destroyed")
    }


}
