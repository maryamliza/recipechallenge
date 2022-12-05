package com.mliza.recipeapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: String = "",
    val name: String = "",
    @SerializedName("image_url") val imageUrl: String = "",
    val description: String = "",
    val ingredients: String = "",
    val preparation: List<String> = listOf(),
    val origin: Location = Location(),
) : Parcelable