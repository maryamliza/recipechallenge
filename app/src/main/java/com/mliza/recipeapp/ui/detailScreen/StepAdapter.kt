package com.mliza.recipeapp.ui.detailScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mliza.recipeapp.databinding.ItemStepBinding


class StepAdapter(
    private val objList: List<String>
) : RecyclerView.Adapter<StepAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemStepBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStepBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = objList[position]
        val binding = holder.binding
        binding.tvStep.text = obj
        binding.tvNumber.text = (position + 1).toString()
    }

    override fun getItemCount(): Int {
        return objList.size
    }
}