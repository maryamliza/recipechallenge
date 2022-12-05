package com.mliza.recipeapp.ui.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mliza.recipeapp.R
import com.mliza.recipeapp.data.models.Recipe
import com.mliza.recipeapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
        viewModel.getRecipes()
    }

    private fun setupObservers() {
        viewModel.listRecipesFiltered.observe(viewLifecycleOwner, Observer {
            binding.ivNoInternet.isVisible = false
            binding.rcRecipe.isVisible = true
            binding.swiperefresh.isRefreshing = false
            binding.rcRecipe.adapter = HomeAdapter(it, ::openRecipeDetail)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            binding.swiperefresh.isRefreshing = false
            showNetworkError()
        })
    }

    private fun openRecipeDetail(recipe: Recipe) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(recipe.id)
        findNavController().navigate(action)
    }

    private fun showNetworkError() {
        binding.ivNoInternet.isVisible = true
        binding.rcRecipe.isVisible = false

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.no_internet_title))
            .setMessage(getString(R.string.no_internet_message))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> }
            .show()
    }

    private fun setupViews() {
        binding.etSearch.addTextChangedListener {
            viewModel.filter(it.toString())
        }
        binding.swiperefresh.setOnRefreshListener {
            viewModel.getRecipes()
        }
    }
}