package com.abb.rickandmorttask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(val image: String,
    val gender: String,
    val species: String,
    val created: String,
    val origin: Origin,
    val name: String,
    val location: Location,
    val episode: List<String>,
    val id: Int,
    val type: String,
    val url: String,
    val status: String
):Parcelable