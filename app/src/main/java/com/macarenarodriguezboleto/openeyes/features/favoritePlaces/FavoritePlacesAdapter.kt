package com.macarenarodriguezboleto.openeyes.features.favoritePlaces

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.macarenarodriguezboleto.openeyes.R

class FavoritePlacesAdapter() :
    RecyclerView.Adapter<FavoritePlacesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePlacesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritePlacesViewHolder(
            layoutInflater.inflate(
                R.layout.item_list_place,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritePlacesViewHolder, position: Int) {
        holder.fillViewHolder()
    }

    override fun getItemCount(): Int = 0


}