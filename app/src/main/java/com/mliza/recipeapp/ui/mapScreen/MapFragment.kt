package com.mliza.recipeapp.ui.mapScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.mliza.recipeapp.R
import com.mliza.recipeapp.databinding.FragmentMapBinding

class MapFragment : Fragment() {
    private val args: MapFragmentArgs by navArgs()
    private lateinit var binding: FragmentMapBinding

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.maps_style))

        val recipe = args.recipe

        val origin = LatLng(recipe.origin.latitude, recipe.origin.longitude)
        val marker = googleMap.addMarker(
            MarkerOptions()
                .position(origin)
                .title(recipe.name)
                .snippet(recipe.origin.country)
        )
        marker?.showInfoWindow()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 14f))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}