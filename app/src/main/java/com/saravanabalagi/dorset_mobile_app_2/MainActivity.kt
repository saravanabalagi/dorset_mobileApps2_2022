package com.saravanabalagi.dorset_mobile_app_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.saravanabalagi.dorset_mobile_app_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
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

    override fun onMapReady(gm: GoogleMap) {
        val sydney = LatLng(-34.0, 151.0)
        val brisbane = LatLng(-27.383333, 153.118332)

        gm.uiSettings.isZoomControlsEnabled = true
        gm.addMarker(
            MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney"))
        gm.addMarker(
            MarkerOptions()
                .position(brisbane)
                .title("Marker in Brisbane")
        )

        gm.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}