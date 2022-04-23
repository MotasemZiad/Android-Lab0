package com.application.lab1

import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var mLocation: Location? = null
    var lat = 0.0
    var lng = 0.0
    private var mFusedLocationClient: FusedLocationProviderClient? = null

    var mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                mLocation = location
            }
            lat = mLocation!!.latitude
            lng = mLocation!!.longitude

            val gaza = LatLng(lat, lng)
            val ccc = LatLng(-34.0, 151.0)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gaza, 16f))

            mMap.addMarker(MarkerOptions().position(gaza).title("Marker"))

            mMap.addPolyline(PolylineOptions().add(gaza, ccc))
            Log.e("MapsActivity", "$lat $lng")
            if (mFusedLocationClient != null) {
                mFusedLocationClient!!.removeLocationUpdates(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mFusedLocationClient!!.requestLocationUpdates(
            getLocationRequest(),
            mLocationCallback,
            Looper.myLooper()
        )

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.isMyLocationEnabled = true

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style))
        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f))


        mMap.setOnMapClickListener { latLng ->
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(latLng).title("Marker"))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
            Toast.makeText(
                this,
                latLng.latitude.toString() + " " + latLng.longitude.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    fun getLocationRequest(): LocationRequest? {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 3000
        return locationRequest
    }

}
