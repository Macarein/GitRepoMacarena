package com.macarenarodriguezboleto.openeyes.features.favoritePlaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.macarenarodriguezboleto.openeyes.R

class FavoritePlacesFragment : Fragment() {

    private lateinit var viewModel: FavoritePlacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_places_fragment, container, false)
    }

}
