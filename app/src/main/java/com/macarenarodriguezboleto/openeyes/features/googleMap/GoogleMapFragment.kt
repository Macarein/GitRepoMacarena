package com.macarenarodriguezboleto.openeyes.features.googleMap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.macarenarodriguezboleto.openeyes.R

class GoogleMapFragment : Fragment() {

    private lateinit var viewModel: GoogleMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.google_map_fragment, container, false)
    }

}
