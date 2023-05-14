package com.macarenarodriguezboleto.openeyes.features.favoritePlaces

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.macarenarodriguezboleto.openeyes.data.model.Place
import com.macarenarodriguezboleto.openeyes.databinding.ItemListPlaceBinding

class FavoritePlacesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemListPlaceBinding.bind(view)

    fun fillViewHolder(element: Place) {
        binding.itemPlaceName.text = element.name
    }
}