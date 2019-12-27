package com.team.animalrescue.maps

import androidx.annotation.DrawableRes

data class MapViewState(val latitude: Double,
                        val longitude: Double,
                        val title: String,
                        @DrawableRes val markerIcon: Int)
