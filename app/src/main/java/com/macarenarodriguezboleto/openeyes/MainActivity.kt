package com.macarenarodriguezboleto.openeyes

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.macarenarodriguezboleto.openeyes.databinding.ActivityMainBinding
import com.macarenarodriguezboleto.openeyes.features.favoritePlaces.FavoritePlacesFragment
import com.macarenarodriguezboleto.openeyes.features.googleMap.GoogleMapFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val googleMapFragment = GoogleMapFragment()
    private val favoritePlacesFragment = FavoritePlacesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)
        setTheme(R.style.Theme_OpenEyes)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        replaceFragment(googleMapFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
//                R.id.map_item -> replaceFragment(googleMapFragment)
                R.id.places_item -> replaceFragment(favoritePlacesFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        binding.bottomNavigationView.visibility = View.VISIBLE
        binding.toolbar.visibility = View.VISIBLE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_fragment_container, fragment)
        transaction.commit()
    }
}

