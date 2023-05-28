package com.macarenarodriguezboleto.openeyes.features.googleMap

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import com.macarenarodriguezboleto.openeyes.data.model.MapsPojo
import com.macarenarodriguezboleto.openeyes.databinding.GoogleMapFragmentBinding

class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: GoogleMapFragmentBinding
    private lateinit var viewModel: GoogleMapViewModel
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    var dataBase: DatabaseReference? = null
    private val tmpRealTimeMarkers = ArrayList<Marker>()
    private val realTimeMarkers = ArrayList<Marker>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GoogleMapFragmentBinding.inflate(layoutInflater)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {}

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
//        miUbicacion()
    }

//    var locListener: LocationListener = object : LocationListener {
//        override fun onLocationChanged(location: Location) {
//            actualizarUbicacion(location)
//        }
//
//        override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {}
//        override fun onProviderEnabled(s: String) {}
//        override fun onProviderDisabled(s: String) {}
//    }
//
//    private fun miUbicacion() {
//        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        val locationManager = context?.getSystemService(LOCATION_SERVICE) as LocationManager
//        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//        actualizarUbicacion(location)
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0f, locListener)
//    }
//
//    private fun actualizarUbicacion(location: Location?) {
//        if (location != null) {
//            dataBase!!.child("usuarios").addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(datasnapshot: DataSnapshot) {
//                    for (marker in realTimeMarkers) {
//                        marker.remove()
//                    }
//                    for (snapshot in datasnapshot.children) {
//                        val mp: MapsPojo? = snapshot.getValue(MapsPojo::class.java)
//                        val lat: Double = mp?.latitud ?: 37.40465434657102
//                        val lng: Double = mp?.longitud ?: -6.007925929678118
//                        val markerOptions = MarkerOptions()
//                        markerOptions.position(LatLng(lat, lng))
//                        map.addMarker(markerOptions)?.let { tmpRealTimeMarkers.add(it) }
//                        val coordenadas = LatLng(lat, lng)
//                        val miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 14f)
//                        map.animateCamera(miUbicacion)
//                    }
//                    realTimeMarkers.clear()
//                    realTimeMarkers.addAll(tmpRealTimeMarkers)
//                }
//
//                override fun onCancelled(error: DatabaseError) {}
//            })
//        }
//    }

    private fun createMarker() {
        val coordinates = LatLng(37.4045157, -6.007550367)
        val marker = MarkerOptions().position(coordinates).title("Campus Camara")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f),
            4000,
            null
        )
        map.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
    }
}
