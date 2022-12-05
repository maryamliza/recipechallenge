package com.mliza.recipeapp.ui.homeScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mliza.recipeapp.data.models.Recipe
import com.mliza.recipeapp.databinding.ItemRecipeBinding


class HomeAdapter(
    private val objList: List<Recipe>,
    private val onItemClicked: (Recipe) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = objList[position]
        val binding = holder.binding
        binding.tvRecipeName.text = obj.name

        Glide.with(binding.ivImage.context)
            .load(obj.imageUrl)
            .into(binding.ivImage)

        binding.container.setOnClickListener {
            onItemClicked(obj)
        }
    }

    override fun getItemCount(): Int {
        return objList.size
    }
}