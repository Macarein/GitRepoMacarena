package com.macarenarodriguezboleto.openeyes.features.splashScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.macarenarodriguezboleto.openeyes.R

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.splash_screen_fragment, container, false)
        startSplashScreen()
        return view
    }

    private fun startSplashScreen() {
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_homeMapFragment)
        }, 5000)
    }
}
