package com.macarenarodriguezboleto.openeyes.features.favoritePlaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.macarenarodriguezboleto.openeyes.R
import com.macarenarodriguezboleto.openeyes.databinding.FavoritePlacesFragmentBinding

class FavoritePlacesFragment : Fragment() {

    private lateinit var binding: FavoritePlacesFragmentBinding
    private lateinit var viewModel: FavoritePlacesViewModel

    private lateinit var adapter: FavoritePlacesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {}
        return inflater.inflate(R.layout.favorite_places_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = FavoritePlacesAdapter(viewModel.placesList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

}
