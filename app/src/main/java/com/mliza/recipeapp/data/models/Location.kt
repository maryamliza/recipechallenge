package com.mliza.recipeapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val country: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) : Parcelable