package com.mliza.recipeapp.ui.detailScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mliza.recipeapp.data.models.Recipe
import com.mliza.recipeapp.databinding.FragmentDetailBinding
import com.mliza.recipeapp.ui.homeScreen.HomeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        val id = args.id
        viewModel.getRecipeDetail(id)
    }


    private fun setupObservers() {
        viewModel.detailRecipe.observe(viewLifecycleOwner) {
            updateRecipeInfo(recipe = it)
        }
    }

    private fun updateRecipeInfo(recipe: Recipe) {
        binding.container.isVisible = true
        binding.loading.isVisible = false

        binding.tvName.text = recipe.name
        binding.tvDescription.text = recipe.description
        binding.tvIngredients.text = recipe.ingredients
        binding.rvPreparation.adapter = StepAdapter(recipe.preparation)

        Glide.with(binding.ivImage.context)
            .load(recipe.imageUrl)
            .into(binding.ivImage)

        binding.btMaps.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMapFragment(recipe))
        }
    }

    private fun openMaps(recipe: Recipe) {
        val latitude = recipe.origin.latitude.toString()
        val longitude = recipe.origin.longitude.toString()
        val ubication = "geo:0,0?q=$latitude,$longitude"

        val gmmIntentUri = Uri.parse(ubication)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun openCall(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    }
}
