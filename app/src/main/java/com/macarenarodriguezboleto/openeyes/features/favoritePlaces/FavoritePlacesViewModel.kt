package com.macarenarodriguezboleto.openeyes.features.favoritePlaces

import androidx.lifecycle.ViewModel
import com.macarenarodriguezboleto.openeyes.data.model.Place

class FavoritePlacesViewModel : ViewModel() {

    lateinit var placesList: List<Place>

    //TODO recoger datos de firebase para poder pasarlo al recyclerView de la vista

}