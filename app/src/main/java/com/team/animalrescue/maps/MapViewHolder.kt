package com.team.animalrescue.maps

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MapViewHolder(
    var context: Context,
    private val mapFragment: SupportMapFragment,
    private val mapZoomLevel: Float,
    private var listener : Listener
) : OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener,
    GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMapClickListener {
    private lateinit var mapViewState: MapViewState
    private var map: GoogleMap? = null
    var marker: Marker? = null

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            this.map = map
            val mapCoordinates = LatLng(this.mapViewState.latitude, this.mapViewState.longitude)
            map.isMyLocationEnabled = true
            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_rescue)))
            map.setMinZoomPreference(mapZoomLevel)
            map.uiSettings.isScrollGesturesEnabled = false
            map.uiSettings.isZoomGesturesEnabled = true
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            map.uiSettings.isZoomControlsEnabled = true
            map.setOnMyLocationChangeListener(this)

            map.setOnMapClickListener { location ->
                if (location != null) {
                    val markerOptions = MarkerOptions()
                    markerOptions.position(location)
                    markerOptions.title(location.latitude.toString() + " : " + location.longitude)
                    map.clear()
                    marker = map.addMarker(markerOptions)
                }
            }

           /* map.setOnCameraMoveListener(OnCameraMoveListener {
                marker?.position = map.cameraPosition.target //to center in map
            })*/
        }
    }

    fun bind(mapViewState: MapViewState) {
        this.mapViewState = mapViewState
        mapFragment.getMapAsync(this)
    }

    fun unbind() {
        // no op
    }

    override fun onMyLocationChange(location: Location) {
        val loc = LatLng(location.latitude, location.longitude)
        if (map != null) {
            marker = map?.addMarker(
                MarkerOptions()
                    .position(loc)
                    .title(mapViewState.title)
            )
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, mapZoomLevel))
        }
        getAddress(loc)
        map?.setOnMyLocationChangeListener(null)
    }

    override fun onMyLocationButtonClick(): Boolean {
        map?.setOnMyLocationChangeListener(this)
        return true
    }

    override fun onMapClick(location: LatLng?) {

    }

    private fun getAddress(location: LatLng) {
        try {
            val geo =
                Geocoder(context, Locale.getDefault())
            val addresses: List<Address> =
                geo.getFromLocation(location.latitude, location.longitude, 1)
            if(addresses.isEmpty()) {
            } else {
                if (addresses.isNotEmpty()) {
                    var address: String =
                        addresses[0].featureName.toString() + ", " + addresses[0].getLocality() + ", " + addresses[0].adminArea + ", " + addresses[0].countryName
                    listener.onAddressUpdate(address)
                    //Toast.makeText(context, address, Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace() // getFromLocation() may sometimes fail
        }
    }

    fun updateLocation(mapViewState: MapViewState){
        val loc = LatLng(mapViewState.latitude, mapViewState.longitude)
        if (map != null) {
            map?.clear()
            marker = map?.addMarker(
                MarkerOptions()
                    .position(loc)
                    .title(mapViewState.title)
            )
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, mapZoomLevel))
        }
    }
    interface Listener {
        fun onAddressUpdate(address: String)
    }
}
