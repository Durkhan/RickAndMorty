package com.abb.rickandmorttask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CharactersInfo(

	@field:SerializedName("results")
	val results: List<Character>,

	@field:SerializedName("info")
	val info: Info? = null
)
@Parcelize
data class Location(
	val name: String? = null,
	val url: String? = null
):Parcelable



data class Info(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("pages")
	val pages: Int? = null,

	@field:SerializedName("prev")
	val prev: String? = null,

	@field:SerializedName("count")
	val count: Int? = null
)
@Parcelize
data class Origin(
	val name: String? = null,
	val url: String? = null
):Parcelable
