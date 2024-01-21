package com.dsoles.mobile.getyourcats.modules.home.data

import com.google.gson.annotations.SerializedName


data class BreedImage(
    @SerializedName("id") var id: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("breeds") var breeds: ArrayList<Breed> = arrayListOf(),
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)