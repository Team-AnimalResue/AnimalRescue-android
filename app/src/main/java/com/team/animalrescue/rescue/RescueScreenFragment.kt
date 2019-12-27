package com.team.animalrescue.rescue

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.SupportMapFragment
import com.team.animalrescue.R
import com.team.animalrescue.databinding.RescueFragmentBinding
import com.team.animalrescue.maps.MapViewHolder
import com.team.animalrescue.maps.MapViewState
import com.team.animalrescue.rescue.adapter.AddPhotosAdapter
import kotlinx.android.synthetic.main.map_view.view.*
import lv.chi.photopicker.PhotoPickerFragment

class RescueScreenFragment : Fragment(), AddPhotosAdapter.Listener, PhotoPickerFragment.Callback, MapViewHolder.Listener {

    private lateinit var binding: RescueFragmentBinding
    private lateinit var viewModel: RescueScreenViewModel
    private lateinit var adapter: AddPhotosAdapter
    private var selectedUriList: ArrayList<Uri>? = null
    private val STORAGE_REQUEST_CODE = 101
    private val LOCATION_REQUEST_CODE = 102
    private val PLACE_PICKER_REQUEST = 111
    private val DEFAULT_MAP_ZOOM_LEVEL = 15f
    private val EMPYT = 0
    private val MAX_LIMIT = 11
    lateinit var mapView: MapViewHolder


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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        allowLocation()
        binding.tvChangeLoc.setOnClickListener {
            openPlacePicker()
        }
    }

    private fun setUpRecyclerView() {
        adapter = AddPhotosAdapter(context)
        adapter.setListener(this)
        binding.addPhotosRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.addPhotosRecyclerView.adapter = adapter
    }

    private fun setupMapView(){
        binding.mapLayout.linearLayout.visibility = View.VISIBLE
        binding.mapLayout.allow_btn.visibility = View.GONE
        val mapFragment = childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        mapView = MapViewHolder(context!!, mapFragment, DEFAULT_MAP_ZOOM_LEVEL, this)
        mapView.bind(MapViewState(0.0,0.0, "Your Location", R.drawable.default_icon))
    }

    override fun onAddClicked() {
        if(adapter.itemCount < MAX_LIMIT){
            setupStoragePermissions(context!!)
        }
        else{
            Toast.makeText(context, "You can not upload more then 10 images.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRemoveClicked(position: Int) {
        Toast.makeText(context, "Method Remove called", Toast.LENGTH_SHORT).show()
        adapter.removeItem(position)
    }

    companion object {
        fun newInstance(): RescueScreenFragment = RescueScreenFragment()
    }


     private fun openImagePicker(maxSelection: Int) {
         PhotoPickerFragment.newInstance(
             multiple = true,
             allowCamera = true,
             maxSelection = maxSelection,
             theme = R.style.CustomPhotoPicker
         ).show(childFragmentManager, "picker")

     }

    private fun setupStoragePermissions(context: Context) {
        val permission = ContextCompat.checkSelfPermission(context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("permissions", "Permission to storage denied")
            makeStorageRequest()
        }
        else{
            openImagePicker(MAX_LIMIT - adapter.itemCount)
        }
    }

    private fun setupLocationPermissions(context: Context) {
        val permission1 = ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_FINE_LOCATION)
        val permission2 = ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        if (permission1 != PackageManager.PERMISSION_GRANTED && permission2 !=PackageManager.PERMISSION_GRANTED) {
            Log.i("permissions", "Permission to storage denied")
            binding.mapLayout.mapLayout.setOnClickListener {
                makeLocationRequest()
            }
        }
        else{
            setupMapView()
        }
    }

    private fun makeStorageRequest() {
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_REQUEST_CODE)
    }

    private fun makeLocationRequest() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out kotlin.String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[EMPYT] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("permissions", "Permission has been denied by user")
                } else {
                    openImagePicker(MAX_LIMIT - adapter.itemCount)
                    Log.i("permissions", "Permission has been granted by user")

                }
            }

            LOCATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[EMPYT] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("permissions", "Permission has been denied by user")
                } else {
                        setupMapView()
                    Log.i("permissions", "Permission has been granted by user")

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                 PLACE_PICKER_REQUEST -> {
                     var place : Place = PlacePicker.getPlace(activity, data)
                     var placeName = place.name
                     var latitude = place.latLng.latitude
                     var longitude = place.latLng.longitude

                     mapView.updateLocation(MapViewState(latitude, longitude, placeName.toString(), R.drawable.default_icon))
                 }
            }
        }
    }

    private fun allowLocation(){
            setupLocationPermissions(context!!)
    }

    override fun onImagesPicked(photos: ArrayList<Uri>) {
        if(photos.size>EMPYT){
            selectedUriList = photos
            adapter.setItems(selectedUriList)
        }
    }

    override fun onAddressUpdate(address: String) {
        binding.tvAddress.text = address
    }

    private fun openPlacePicker() {
        val builder = PlacePicker.IntentBuilder()
        startActivityForResult(builder.build(activity), PLACE_PICKER_REQUEST)

      /*  try {
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }*/
    }

}
