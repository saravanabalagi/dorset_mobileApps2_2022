package com.saravanabalagi.dorset_mobile_app_2

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.saravanabalagi.dorset_mobile_app_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var map: GoogleMap
    private val LOCATION_REQUEST_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.helloWorldTextView.text = getString(R.string.hello_android)
        Log.i("MainActivity", binding.helloWorldTextView.text.toString())

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.main_fragment, MainFragment().apply {
                arguments = Bundle().apply {
                    putString("NEW_TEXT", "Text Changed in Fragment")
                }
            })
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            enableLocation()
            return
        }
        val permissions = arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        requestPermissions(permissions, LOCATION_REQUEST_CODE)
    }

    @SuppressLint("MissingPermission")
    private fun enableLocation() {
        map.isMyLocationEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED ||
                        grantResults[1] == PackageManager.PERMISSION_GRANTED
                    ) {
                        enableLocation()
                    } else Snackbar.make(
                        binding.root,
                        "Location features will not work",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onMapReady(gm: GoogleMap) {
        map = gm

        val sydney = LatLng(-34.0, 151.0)
        val brisbane = LatLng(-27.383333, 153.118332)

        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isMapToolbarEnabled = false
        checkLocationPermission()

        map.setOnMapClickListener { latLng ->
            val snackBar =
                Snackbar.make(binding.root, "Do you want to add a marker?", Snackbar.LENGTH_LONG)
            snackBar.setAction("Add") {
                map.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(binding.mapMarkerEditText.text.toString())
                )
                binding.mapMarkerEditText.setText("")
            }
            snackBar.show()
        }

        map.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        map.addMarker(
            MarkerOptions()
                .position(brisbane)
                .title("Marker in Brisbane")
        )

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10F))
    }
}